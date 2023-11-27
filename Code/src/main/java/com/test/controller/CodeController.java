package com.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.domain.CodeDTO;
import com.test.persistence.CodeDAO;

@Controller
public class CodeController {

	@Autowired
	private CodeDAO dao;
	
	@GetMapping(value="/add.do")
	public String add() {
		
		return "add";
	}

	@PostMapping(value="/addok.do")
	public String addok(CodeDTO dto) {
		
		int result =  dao.add(dto);
		
		if (result == 1) {
			return "redirect:/list.do";
			//여기(redirect:)에는 root context를 적지 않는다.
			//resp.sendRedirect에는 root context를 적는다.
			//? WOW 일관성이 없네. 어렵네.
		} else {
			return "redirect:/add.do";
		}
		
	}
	
	@GetMapping(value="/list.do")
	public String list(Model model, @RequestParam(name="language", required=false) List<String> language) {
		
		//- list.do
		//- list.do?language=java& //(뒤의 &는 추가로 붙든 안붙든 상관 없다.)
		//- list.do?language=java&language=css
		System.out.println(language);
		
		List<CodeDTO> list = dao.list(language);
		
		//SELECT 한걸 jsp로 넘겨야 하니 Model이 필요하다!
		model.addAttribute("list", list);
		model.addAttribute("language", language); //버튼 클릭할때마다 새로고침하기 때문에 누적 처리를 하기 위한 장치
		
		return "list";
	}
	
	@GetMapping(value="/view.do")
	public String view(Model model, String seq) {
		
		CodeDTO dto = dao.get(seq);
		
		model.addAttribute("dto", dto);
		
		return "view";
	}
}
