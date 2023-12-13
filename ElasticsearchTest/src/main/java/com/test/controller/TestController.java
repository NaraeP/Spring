package com.test.controller;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TestController {

	//목록보기
	@GetMapping(value = "/list.do")
	public String list(Model model) {

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
			searchSourceBuilder.query(QueryBuilders.matchAllQuery()); //날릴 쿼리 종류 지정
			
			searchRequest.source(searchSourceBuilder); 
			
			//실제로 쿼리를 날리는건 client 객체가 진행!
			//실제 검색 요청
			SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
			//RequestOptions.DEFAULT 는 옵션인데 수업중에는 건들일이 없으므로 default로 주면 된다.
			
			SearchHits searchHits = searchResponse.getHits();
			
			for (SearchHit hit : searchHits) {
				System.out.println(hit);
			}
			
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
	public String addok(Model model) {

		return "addok";
	}
}
