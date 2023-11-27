package com.test.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//컨트롤러 구현 방식
//1. Controller 인터페이스 구현
//2. @Controller 어노테이션 사용

//1. Controller 인터페이스 구현 방식
//public class Ex03Controller implements Controller {
//
//	@Override
//	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//}

//2. @Controller 어노테이션 사용 > 어떤 요소 앞에 @키워드로 작성. 어노테이션 + 코드를 한줄에 써도 무관하나, 가독성이 떨어져서 어노테이션 + 개행 후, 코드 작성 방식이 일반적
//특징: 인터페이스를 상속받지 않았기에 handleRequest가 없다.
//Q. 요청메서드(doGet/doPost/handleRequest)가 없는데 어떻게 하지?
//A. 내맘대로 만들면 된다.
@Controller
@RequestMapping(value = "/ex03.do")
public class Ex03Controller {
	
	//요청메서드 > 맘대로~ 만들엉~
	//Q. 그런데 메서드가 여러개면? 스프링은 강제로 하나의 메서드를 호출해야하는데, 여러개의 메서드 중 요청 메서드가 무엇인지 어떻게 알지?
	//A. 핸들러 메서드를 만들기 위한 최소한의 약속 > 어노테이션을 붙인다. > @RequestMapping > 일반 메서드가 아닌 응답 메서드에 어노테이션을 붙이면 된다.
	
	//요청 메서드 == 핸들러
	@RequestMapping
	public String test() {
		
		//업무 코드
		
		//JSP 호출하기 > ModelAndView
		//Q. 어? 그럼 호출용 ModelAndView를 만들어서 리턴해야하나?
		//A. 해도 되나, return "ex03" 하면 끝~~
		//문자열("ex03")을 반환하는 과정에서,
		//1. ModelAndView 생성
		//2. ViewResolver 동작
		//3. 리턴 발생(jsp파일명만 반환하면 된다!)
		
		return "ex03";
	}

	//만약 @RequestMapping 어노테이션을 하나 이상의 메서드에 붙일 경우, 하기와 같은 에러가 발생한다.
	//Ambiguous mapping found. Cannot map 'ex03Controller' bean method
	public int sum(int a, int b) {
		
		return a + b;
	}
	
}