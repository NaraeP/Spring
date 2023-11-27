package com.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

	@GetMapping(value = "/test.do")
	public String test(Model model) {

		//http://localhost:8090/tiles/test.do
		
		//- 기존 ViewResolver 삭제 > 컨트롤러가 돌려주는 이름이 JSP명 이었다가
		//- 새롭게 TilesViewResolver(사실 이런 이름 아니나 편의상 사용) 추가 > definition명
		return "test";
	}
}
