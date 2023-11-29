package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.domain.AddressDTO;
import com.test.persistence.AddressDAO;

//@RestController > 요청 메서드의 반환값에 자동으로 @ResponseBody가 적용된다.
@RestController
public class RESTController {

	@Autowired
	AddressDAO dao;
	
	//CRUD + REST
	
	//추가하기
	//1. 주소: http://localhost:8090/rest/address
	//2. 행동(Method): POST
	//3. 반환값(Return): int (실패: 0, 성공: 1)
	
	//@RequestMapping(value="/address", method=RequestMethod.POST)
	@PostMapping(value="/address")
	public int add(@RequestBody AddressDTO dto) {
		return dao.add(dto);
	}
	
	
	
}
