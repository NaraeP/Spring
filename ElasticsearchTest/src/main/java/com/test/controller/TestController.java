package com.test.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.xcontent.XContentType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestController {

	//목록보기
	@GetMapping(value = "/list.do")
	public String list(Model model, String type, String word, String word2, @RequestParam(defaultValue="0") int slop) {

		if (type == null || type.equals("")) {
			//전체목록
			type = "match_all";
					
		}
		
		
		
		
		//try catch 예외처리 필수로 들어감!
		try {
			
			//builder: 엘라스틱서치 연결 정보 객체
			//- client 객체 생성에 builder 필요 > builder 객체 생성에 host 필요 > host 객체 생성

			//HttpHost host = new HttpHost("엘라스틱서치IP", "포트번호", "프로토콜");
			HttpHost host = new HttpHost("172.28.230.214", 9200, "http");
			
			//RestClientBuilder builder =  RestClient.builder("엘라스틱서치 IP 주소");
			RestClientBuilder builder =  RestClient.builder(host);
			
			//client: 엘라스틱서치와 대화 객체
			RestHighLevelClient client = new RestHighLevelClient(builder); //공식 도움말에있는 API 임에도 취소선이 그어진다.
			
			//접근하려는 인덱스 참조 > spring 인덱스
			SearchRequest searchRequest = new SearchRequest("spring");
			
			SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder().size(100); //개수..?무슨 개수..? 개수정도로만..이라고 쌤이 말씀하셨는뎅..
			
			//*** 엘라스틱서치 검색 기능
			// - GET spring/_search
			//	 {
			//		"query": {
			//			"match_all": {}
			//		}
			//	 }
			
			if (type.equals("match_all")) {
				
				searchSourceBuilder.query(QueryBuilders.matchAllQuery()); //날릴 쿼리 종류 지정
			} else if (type.equals("match_or")) {
				
				//match > 풀텍스트 검색
				searchSourceBuilder.query(QueryBuilders.matchQuery("message", word));
			} else if (type.equals("match_and")) {
				
				//match + operator(and)
				searchSourceBuilder.query(QueryBuilders.matchQuery("message", word).operator(Operator.AND));
			} else if (type.equals("match_phrase")) {
				
				//match phrase
				if (slop == 0) {
					searchSourceBuilder.query(QueryBuilders.matchPhraseQuery("message", word));
				} else {
					searchSourceBuilder.query(QueryBuilders.matchPhraseQuery("message", word).slop(slop));
				}
				
			} else if (type.equals("match_bool")) {
				//searchSourceBuilder.query(QueryBuilders.boolQuery().must(반드시 포함될 조건).mustNot(반드시 제거할 조건));
				searchSourceBuilder.query(QueryBuilders.boolQuery()
						.must(QueryBuilders.matchQuery("message", word))
						.mustNot(QueryBuilders.matchQuery("message", word2)));
				
			} else if (type.equals("match_should")) {
				searchSourceBuilder.query(QueryBuilders.boolQuery()
							.must(QueryBuilders.matchQuery("message", word))
							.should(QueryBuilders.matchQuery("message", word))
							);
			}
			
			
			
			searchRequest.source(searchSourceBuilder); 
			
			//실제로 쿼리를 날리는건 client 객체가 진행!
			//실제 검색 요청
			SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
			//RequestOptions.DEFAULT 는 옵션인데 수업중에는 건들일이 없으므로 default로 주면 된다.
			
			SearchHits searchHits = searchResponse.getHits();
			
			for (SearchHit hit : searchHits) {
				System.out.println(hit);
			}
			
			
			//SearchHits > (변환) > List<HashMap>
			//하나의 도큐먼트가 JSON object로 되어있어서, java로 변환하려면 DTO를 만들던지, 그에 준하는 HashMap을 만들던지 > HashMap이 여러개니까 List로 받기(List가 INDEX, HashMap이 document라고 생각하면 된다.)
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			
			for (SearchHit hit : searchHits) {
				
				//Document > HashMap
				Map<String, Object> map = hit.getSourceAsMap(); // 왜 Object? > 숫자가 있을 수도, 문자가 있을 수도 있기 때문에
				map.put("id", hit.getId()); 
				map.put("score", hit.getScore()); 
				list.add(map);
			}
			
			model.addAttribute("list", list);
			model.addAttribute("word", word);
			model.addAttribute("word2", word2);
			model.addAttribute("type", type);
			model.addAttribute("slop", slop);
			
			//사용 종료 > 엘라스틱서치 접속 종료
			client.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "list";
	}
	
	
	//추가하기(폼)
	@GetMapping(value = "/add.do")
	public String add(Model model) {

		return "add";
	}
	
	//추가하기(처리)
	@PostMapping(value = "/addok.do")
	public String addok(Model model, String id, String message) {

		try {
			
			//client 객체
			RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("아이피", 9200, "http")));
			
			/*
				
				PUT spring/_doc/11
				{
					"message": "안녕하세요. 홍길동입니다."
				}

			 */
			
			String data = String.format("{ \"message\": \"%s\"}", message); //Java는 JSON이 뭔지 모른다. JSON은 데이터를 표기하는 문자열 표기법 중 하나. 따라서 Java는 JSON을 문자열 취급할 수 밖에 없다.
			
			
			IndexRequest indexRequest = new IndexRequest("spring").source(data, XContentType.JSON).setRefreshPolicy("wait_for"); //source(전송할데이터 > JSON 객체 {} 1개)			
			indexRequest.id(id);
			
			//실제로 엘라스틱서치에 데이터(도큐먼트)를 추가하기
			client.index(indexRequest, RequestOptions.DEFAULT);
			
			client.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/list.do";
	}
}
