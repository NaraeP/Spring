package com.test.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

//서블릿(X) > 서블릿 > 컨트롤러 역할(O) == <bean>
public class Ex01Controller implements Controller {

	//doGet/doPost == handleRequest
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//업무 진행..
		
		//뷰 호출(JSP)
		//- RequestDispatcher > forward()
		
		//- ModelAndView 사용
		// - Model > 데이터 전송
		// - View > JSP
		
		ModelAndView mv = new ModelAndView();
//		mv.setViewName("JSP 경로");
//		mv.setViewName("/WEB-INF/views/ex01.jsp");
		mv.setViewName("ex01");
		
		
		//비권장 방식
		request.setAttribute("name", "홍길동");
		//위의 방식 그대로 사용해도 되나, 권장하지 않는다.(Why? 환경이 바뀌었기 때문에 권장 방식을 사용할 것!)
		
		//권장 방식
		mv.addObject("age", 20);
		
		
		return mv; //jsp 호출 행동 > RequestDispatcher > forward()
		
		//http://localhost:8090/spring/ex01.do
		
	}

}
