package com.test.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect //보조 업무 객체
public class log {
	
	//포인트컷 > 주 업무 메서드 > AspectJ 표현식
	@Pointcut("execution(* com.test.controller.AOPController.add(..))")
	public void pc1() {
		//구현부 없음(@Pointcut이 필요해서 만든 메서드라 아무 일도 하지 않는다.)
	}
	
	//보조 업무
	@After("pc1()")
	public void m1() {
		System.out.println("[보조업무] 기록을 남깁니다.");
	}
	
	@Before("pc1()")
	public void m2() {
		System.out.println("Before Advice");
	}
	
	@After("execution(* com.test.controller.AOPController.list(..))")
	public void m3() {
		System.out.println("보조 업무");
	}
	
	
	//글쓰기, 수정하기, 삭제하기 > 권한 체크
	//방문 기록
	
	
}
