package com.ssafy.happyhouse.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.MemberDto;

@Mapper
public interface LoginDao {

	MemberDto login(MemberDto memberDto);
	
}
