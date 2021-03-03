package com.ssafy.happyhouse.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.ssafy.happyhouse.model.MemberDto;

@Mapper
public interface UserDao {

	void add(MemberDto memberdto);
	
	List<MemberDto> searchAll();
	
	MemberDto search(String id);
	
	void update(MemberDto memberdto);
	
	void delete(String id);
}
