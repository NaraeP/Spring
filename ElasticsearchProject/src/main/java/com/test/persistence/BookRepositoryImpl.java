package com.test.persistence;

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
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.xcontent.XContentType;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.domain.BookDTO;

@Repository
public class BookRepositoryImpl implements BookRepository {

	@Override
	public List<Map<String, Object>> search(String word) {
		
		try {
			
			List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
			
			//RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("172.28.230.214", 9200, "http")));
			
			//AWS에 올리기 위해 localhost로 변경
			RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));
			
			//인덱스 선택
			SearchRequest searchRequest = new SearchRequest("book");
			
			SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder().size(100);
			
			//*** 검색 쿼리
			//- 검색에서 가장 흔한 패턴
			//- bool query (must(match 검색어) + should(match_phrase 검색어)) :: 잘 모르겠다면 이 검색 사용하기 > 무난한 검색)
			//should는.....
			searchSourceBuilder.query(
				QueryBuilders.boolQuery()
					.must(QueryBuilders.matchQuery("title", word))
					.should(QueryBuilders.matchPhraseQuery("title", word))
			
			);
			
			searchRequest.source(searchSourceBuilder);
			
			//실제 검색 요청
			SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
			
			SearchHits searchHits = searchResponse.getHits();
			
			for (SearchHit hit : searchHits) {
				
				Map<String, Object> map = hit.getSourceAsMap();
				map.put("id", hit.getId());
				map.put("score", hit.getScore());
				list.add(map);
				
			}
			
			return list;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

	@Override
	public void add(BookDTO dto) {

		try {
			
			ObjectMapper om = new ObjectMapper();
			
			//DTO.toString() > JSON String
			String data = om.writeValueAsString(dto);
			
			
			RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));
			
			IndexRequest request = new IndexRequest("book")
										.source(data, XContentType.JSON)
										.setRefreshPolicy("wait_for");
			
			request.id(dto.getSeq());
			
			client.index(request, RequestOptions.DEFAULT);
			
			client.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}
