package com.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping(value = "/index.do")
	public String index(Model model) {

		return "index";
		//더 이상 return값은 jsp가 아니라, tiles.xml가 관리하고 있는 <definition>의 name 값
		
	}
	
	
}
