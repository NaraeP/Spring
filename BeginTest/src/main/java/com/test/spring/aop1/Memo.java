package com.test.spring.aop1;

public interface Memo {

	//아래 4가지 행동이 실행될 때마다 언제 발생했는지 기록!!! > Log
	
	
	//메모 쓰기
	void add(String memo);
	
	//메모 읽기
	//String read(int seq);
	String read(int seq) throws Exception;
	
	//메모 수정(성공 true, 실패 false 반환)
	boolean edit(int seq, String memo);
	
	//메모 삭제
	boolean del(int seq);
	
}
