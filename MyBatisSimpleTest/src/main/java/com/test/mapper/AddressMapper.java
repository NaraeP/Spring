package com.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.test.domain.AddressDTO;

public interface AddressMapper {

	//목록보기
	@Select("select * from tblAddress")
	List<AddressDTO> list();
	
	AddressDTO get();
}
