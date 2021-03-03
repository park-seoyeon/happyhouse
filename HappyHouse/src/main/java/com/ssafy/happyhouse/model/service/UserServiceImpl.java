package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.MemberDto;
import com.ssafy.happyhouse.model.dao.UserDao;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private SqlSession session;
	
	@Override
	public void add(MemberDto memberdto) {
		session.getMapper(UserDao.class).add(memberdto);

	}

	@Override
	public List<MemberDto> search() {
		return session.getMapper(UserDao.class).searchAll();
	}

	@Override
	public MemberDto search(String id) {
		return session.getMapper(UserDao.class).search(id);
	}

	@Override
	public void update(MemberDto memberdto) {
		session.getMapper(UserDao.class).update(memberdto);
	}

	@Override
	public void delete(String id) {
		session.getMapper(UserDao.class).delete(id);
	}

}
