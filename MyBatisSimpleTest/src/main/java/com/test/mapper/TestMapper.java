package com.test.mapper;

import org.apache.ibatis.annotations.Select;

//인터페이스 매퍼
public interface TestMapper {

	//어노테이션 방식으로 SQL 구현
	//- 장점: 편리함
	//- 단점: 불편함 > SQL 문자열 표현
	//?!?!?!?!?!?!?!
	@Select("select count(*) from tblAddress")
	String getTime();
	
	//엥 이번에는 이게 끝이다.
	//쿼리 안짠다.
	String getTime2();
}
