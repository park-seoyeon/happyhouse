package com.ssafy.happyhouse.model.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.MemberDto;
import com.ssafy.happyhouse.model.dao.LoginDao;

@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	private SqlSession session;

	@Override
	public MemberDto login(MemberDto memberDto) {
		System.out.println(memberDto.getUserid());
		System.out.println(memberDto.getUserpwd());
		MemberDto temp = session.getMapper(LoginDao.class).login(memberDto);
		System.out.println("성공?");
		System.out.println(temp);
		return session.getMapper(LoginDao.class).login(memberDto);
	}

}
