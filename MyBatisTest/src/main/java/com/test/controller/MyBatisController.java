package com.test.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.domain.MyBatisDTO;
import com.test.persistence.MyBatisDAO;

@Controller
public class MyBatisController {

	@Autowired //얘를 찾아서 인스턴스를 만들고 dao 안에 넣어준다.(new 생성자 역할 대신)
	private MyBatisDAO dao;
	
	@GetMapping(value="/test.do")
	public String test() {
		
		System.out.println("test");
		
		//의존 객체 > Spring DI
		//MyBatisDAO dao = new MyBatisDAOImpl();
		//얘도 어노테이션으로 퉁쳐야한다! > @Autowired
		
		dao.test();
		
		return "list";
	}
	
	@GetMapping(value="/add.do")
	public String add() {
		
		return "add";
		
	}

	@PostMapping(value="/addok.do")
	public String addok(MyBatisDTO dto, Model model) {
		
		//이제와서 인코딩한다고 req, resp를 다시 꺼내긴 좀 그래~
		//그래서 filter 처리할 예정!!
		
		int result = dao.add(dto);
		
		model.addAttribute("result", result);
		
		return "addok";
		
	}
	
	
	//반환값(X), 매개변수(X)
	//반환값(X), 매개변수(O)
	//반환값(O), 매개변수(X)
	//   - 반환값: 단일값
	//   - 반환값: 레코드 1줄
	//   - 반환값: 레코드 N줄
	// 반환값(O), 매개변수(O)
	//   - 반환값: 단일값
	//   - 반환값: 레코드 1줄
	//   - 반환값: 레코드 N줄
	
	//1. 반환값(X), 매개변수(X)
	@GetMapping(value = "/m1.do")
	public String m1() {
      
		this.dao.m1();
		  
		return "list";
      
	}
	
	//2-1. 반환값(X), 매개변수(O)
	@GetMapping(value="/m2.do")
	public String m2(@RequestParam(name="seq") String seq, Model model) {
		
		//변수가 하나 들어갔다는것은 매개변수로 내가 받아오겠다는 것.
		//GetMapping이므로 필연적으로 seq는 쿼리스트링이 된다.
		//m2.do?seq=5
		
		//delete from tblMyBatis where seq = ?
		int result = this.dao.m2(seq); //성공: 1, 실패: 0
		
		model.addAttribute("result", result);
		
		return "list";
	}
	
	//2-2. 반환값(X), 매개변수(O) > 많은 양의 데이터(dto) 매개변수로 전달하기
	@GetMapping(value="/m3.do")
	public String m3(Model model) {
		
		MyBatisDTO dto = new MyBatisDTO();
		dto.setName("가가가");
		dto.setAge("25");
		dto.setAddress("서울시 강남구 대치동");
		dto.setGender("m");
		
		int result = this.dao.m3(dto);
		
		model.addAttribute("result", result);
		
		return "list";
	}
	
	//2. 반환값(X), 매개변수(O) > 많은 양의 데이터(자주 쓰이지 않는 데이터)를 HashMap 형태의 매개변수로 전달하기
	@GetMapping(value="/m4.do")
	public String m4(Model model) {
		
		//update tblMyBatis set address = ? where seq = ?;
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("seq", "4");
		map.put("address", "서울시 강남구 역삼동");
		
		int result = this.dao.m4(map);
		
		model.addAttribute("result", result);
		
		return "list";
	}
	
	//3. 반환값(O), 매개변수(X)
	//3.1 단일값 반환(1행 1열)
	//- select count(*) from tblMyBatis
	@GetMapping(value="/m5.do")
	public String m5(Model model) {
		
		int count = this.dao.m5();
		
		model.addAttribute("count", count);
		
		return "list";
	}
	
	//3. 반환값(O), 매개변수(X)
	//3.2 레코드 1개 반환(컬럼 N개)
	//- select * from tblMyBatis where seq = ?
	@GetMapping(value="/m6.do")
	public String m6(String seq, Model model) {
		
		MyBatisDTO dto = this.dao.m6(seq);
		
		model.addAttribute("dto", dto);
		
		return "list";
	}
	
	//3. 반환값(O), 매개변수(X)
	//3.3 레코드 N개 반환(컬럼 1개)
	//- select name from tblMyBatis;
	@GetMapping(value="/m7.do")
	public String m7(Model model) {
		
		List<String> names = this.dao.m7();
		
		model.addAttribute("names", names);
		
		return "list";
	}
	
	//3. 반환값(O), 매개변수(X)
	//3.4 레코드 N개 반환(컬럼 N개)
	//- select * from tblMyBatis;
	@GetMapping(value="/m8.do")
	public String m8(Model model) {
		
		List<MyBatisDTO> list = this.dao.m8();
		
		model.addAttribute("list", list);
		
		return "list";
	}
	
	@GetMapping(value="/m9.do")
	public String m9(String table, Model model) {
		
		//m9.do?table=employees
		//select count(*) from employees

		//m9.do?table=tblMyBatis
		//select count(*) from tblMyBatis
		
		int count = this.dao.m9(table);
		
		model.addAttribute("count", count);
		
		return "list";
	}

	@GetMapping(value="/m10.do")
	public String m10(Model model) {
		
		int count = this.dao.m10();
		
		model.addAttribute("count", count);
		
		return "list";
	}

	@GetMapping(value="/m11.do")
	public String m11(String word, Model model) {
		
		//주소 검색
		List<MyBatisDTO> list = this.dao.m11(word);
		
		model.addAttribute("list", list);
		
		return "list";
	}

	@GetMapping(value="/m12.do")
	public String m12(Model model, String type) {
		
		//동적 쿼리
		
		//m12.do?type=1 > select first_name from employees
		//m12.do?type=2 > select last_name from employees
		
		List<String> names = dao.m12(type);
		
		model.addAttribute("names", names);
		
		return "list";
	}

	@GetMapping(value="/m13.do")
	public String m13(Model model, String column, String word) {
		
		//1. 이름: m13.do?column=name&word=홍길동
		//2. 주소: m13.do?column=address&word=역삼
		//3. 나이: m13.do?column=age&word=25
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("column", column);
		map.put("word", word);
		
		List<MyBatisDTO> list = this.dao.m13(map);
		
		model.addAttribute("list", list);
		
		return "list";
	}
	
	
	

	
	
}
