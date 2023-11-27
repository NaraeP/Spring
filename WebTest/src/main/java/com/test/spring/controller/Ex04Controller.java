package com.test.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping(value="/ex04.do")
public class Ex04Controller {

	@RequestMapping(value="/ex04.do")
	public String ex04() {
		
		return "ex04";
	}
	
	//1개의 컨트롤러에 여러개의 가상 주소를 매핑하는 방법
	//그냥 메서드가 아닌 요청 메서드를 하나 더 생성 후, 주소도 하나 더 생성한다.(1개는 위의 메서드를 호출하는데 사용, 다른 1개는 아래의 메서드를 호출하는데 사용)
	//방법은? 각각의 메서드의 @RequestMapping에 value로 주소를 붙인다.
	@RequestMapping(value="/ex04_1.do")
	public String ex04_1() {
		
		return "ex04_1";
	}
}
