package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.MemberDto;

public interface UserService {

	void add(MemberDto memberdto);
	
	List<MemberDto> search();
	
	MemberDto search(String id);
	
	void update(MemberDto memberdto);
	
	void delete(String id);
}
