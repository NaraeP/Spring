package com.test.domain;

import java.util.List;

import lombok.Data;

@Data
public class MemberDTO {
	
	private String userid;
	private String userpw;
	private String username;
	private String regdate;
	private boolean enabled;
	
	//일반회원일 경우 ROLE_MEMBER, 관리자일 경우 ROLE_MEMBER,ROLE_ADMIN 2개 들어있다!
	private List<AuthDTO> authlist;
}
