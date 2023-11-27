package com.test.spring.controller;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.spring.domain.SpringDTO;
import com.test.spring.persistence.SpringDAO;
import com.test.spring.persistence.SpringDAOImpl;

@Controller
public class Ex05Controller {

//	@RequestMapping(value="/ex05.do", method=RequestMethod.GET)
//	public String ex05() {
//		
//		//데이터 입력 폼
//		
//		return "ex05";
//	}
//	
//	@RequestMapping(value="/ex05ok.do", method=RequestMethod.POST)
//	public String ex05ok() {
//		return "ex05ok";
//	}
	
	//위의 과정을 조금 더 간단하게!!
	//스프링 4.XX 이상부터 나온 어노테이션 사용!
	@GetMapping(value="/ex05.do")
	public String ex05() {
		
		//데이터 입력 폼
		
		return "ex05";
		
	}
	
	
	//이건 Servlet 방식 > 가능은 하다. 하지만 비권장
//	@PostMapping(value="/ex05ok.do")
//	public String ex05ok(HttpServletRequest req, HttpServletResponse resp, HttpSession session) {
//		
//		//데이터 수신
//		//- Servlet/JSP: req.getParameter(), req.getParameterValues()
//		//인자값이 없어서 쓸 수 없다. 그럼 어떻게 하지? > 인자값을 만들면 된다.
//		//내가 만든 메서드를 스프링이 호출하는데, 정해진 규칙에 따라 메서드를 만들어야 하기 때문에 내 맘대로 인자를 넣었다 뺐다 하면 원래는 안.되.지.만! 스프링은 굉장히 잘 만들어진 유연한 프레임워크이기 때문에 내가 어떻게 하든 잘 대처한다. ex) 인자가 없으면 인자없이 메서드 호출, 인자가 있는 경우 인자와 함께 메서드 호출 등 찰떡같이 대처한다.
//		
//		//심지어 내가 개떡같이 req, resp 순서를 바꿔 넣어도 스프링이 알아서 찰떡같이 알아듣는다.
//		
////		System.out.println(req == null);
////		System.out.println(resp == null);
////		System.out.println(session == null);
//		
//		String data = req.getParameter("data");
//		
//		req.setAttribute("data", data);
//		
//		
//		return "ex05ok";
//	}
	
	//Spring 방식(1차)
	/*
	@PostMapping(value="/ex05ok.do")
	public String ex05ok(@RequestParam(name="data") String data, Model model) {
		
		//String data = req.getParameter("data"); > Servlet 방식
		
		//Spring 방식을 이용해보자!!
		//: request 데이터를 집어넣을 변수를 매개변수로 생성하고, 어노테이션 작성
		//(@RequestParam(name="age") String data)
		
//		System.out.println(data);
		
		//데이터를 넘길 땐?
		//매개변수에 model을 집어넣기
		
		//req.setAttribute("data", data);
		model.addAttribute("data", data);
		
		
		return "ex05ok";
	}
	*/
	
	
	//Spring 방식(2차)
	/*
	@PostMapping(value="/ex05ok.do")
	public String ex05ok(String data, Model model) {
		
		//어노테이션 생략이 가능하다.
		//어? 그럼 어떻게 알고? 접점이 뭔데? > 변수명
		//(조건: 받아올 데이터의 'name'과 '변수명' 이 동일해야 가져올 수 있다.)
		
		//Model은 생략 불가!!
		
		model.addAttribute("data", data);
		
		return "ex05ok";
	}
	*/

	//Spring 방식 > 복합값 전송하기(1차)
	/*
	@PostMapping(value="/ex05ok.do")
	public String ex05ok(@RequestParam("name") String name, 
						 @RequestParam("age") String age, 
						 @RequestParam("address") String address, 
						 Model model) {
		
		SpringDTO dto = new SpringDTO();
		
		dto.setName(name);
		dto.setAge(age);
		dto.setAddress(address);
		
		SpringDAO dao = new SpringDAOImpl();
		
		int result = dao.add(dto);
		
		model.addAttribute("result", result);
		
		
		
		return "ex05ok";
	}
	*/
	
	//Spring 방식 > 복합값 전송하기(2차)
	/*
	@PostMapping(value="/ex05ok.do")
	public String ex05ok(String name, String age, String address, Model model) {
		
		SpringDTO dto = new SpringDTO();
		
		dto.setName(name);
		dto.setAge(age);
		dto.setAddress(address);
		
		SpringDAO dao = new SpringDAOImpl();
		
		int result = dao.add(dto);
		
		model.addAttribute("result", result);
		
		
		
		return "ex05ok";
	}
	*/
	
	//Spring 방식 > 복합값 전송하기(3차)
	/*
	@PostMapping(value="/ex05ok.do")
	public String ex05ok(SpringDTO dto, String seq, Model model) {

		//set자를 떼고 소문자로 바뀐 이름과 name의 이름이 동일하면 dto에 알아서 다 들어간다.
		//WOW....d
		
		SpringDAO dao = new SpringDAOImpl();
		
		int result = dao.add(dto);
		
		System.out.println(seq);
		
		model.addAttribute("result", result);
		
		
		return "ex05ok";
	}
	*/

	//Spring 방식 > 다중값(배열) 전송하기(Servlet 방식 ver.)
	/*
	@PostMapping(value="/ex05ok.do")
	public String ex05ok(HttpServletRequest req, Model model) {
		
		String[] cb = req.getParameterValues("cb");
		
		System.out.println(Arrays.toString(cb));
		
		
		return "ex05ok";
	}
	*/

	//Spring 방식 > 다중값(배열) 전송하기(Spring 방식 ver.)
	/*
	@PostMapping(value="/ex05ok.do")
//	public String ex05ok(@RequestParam("cb") String[] cb, Model model) {
	public String ex05ok(String[] cb, Model model) {
		
		System.out.println(Arrays.toString(cb));

		return "ex05ok";
	}
	*/
	
	//Spring 방식 > jsp에 넘기는 과정까지도 포함된 어노테이션(쌤은 잘 안쓰게 되신다고 함!)
	@PostMapping(value="/ex05ok.do")
	public String ex05ok(@ModelAttribute(name="data") String data) {
		return "ex05ok";
	}
	
}
	

