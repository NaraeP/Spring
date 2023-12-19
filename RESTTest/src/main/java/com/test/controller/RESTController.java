package com.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	//목록보기
	//1. 주소: http://localhost:8090/rest/address
	//2. 행동(Method): GET
	//3. 반환값(Return): List<DTO> (JSON 형태)
	
	@GetMapping(value="/address")
	public List<AddressDTO> list() {
		return dao.list();
	}
	
	//수정하기
	//1. 주소: http://localhost:8090/rest/address/1(-> parameter | 'seq=1'의 역할)
	//2. 행동(Method): PUT or PATCH
	//3. 반환값(Return): int(실패: 0, 성공: 1)
	
//	@RequestMapping(value="/address/{seq}", method=RequestMethod.PUT) //경로 변수(PathVariable)
	@PutMapping(value="/address/{seq}")
	public int edit(@RequestBody AddressDTO dto, @PathVariable("seq") String seq) { //add()와 다른 점: 데이터 + 기본키 넘기기
		
		//자바에서 seq는 어떻게 알아내지? > parameter로 받기
		
		dto.setSeq(seq);
		
		return dao.edit(dto);
	}
	
	//삭제하기
	//1. 주소: http://localhost:8090/rest/address/1
	//2. 행동(Method): DELETE
	//3. 반환값(Return): int(실패: 0, 성공: 1)
	
	@DeleteMapping(value="/address/{seq}")
	public int del(@PathVariable("seq") String seq) {
		return dao.del(seq);
	}
	
	//검색하기(= 상세보기)
	//1. 주소: http://localhost:8090/rest/address/1
	//2. 행동(Method): GET
	//3. 반환값(Return): DTO
	
	@GetMapping(value = "/address/{seq}")
	public AddressDTO get(@PathVariable("seq") String seq) {
		return dao.get(seq);
	}
	
	
	
	
	
}
