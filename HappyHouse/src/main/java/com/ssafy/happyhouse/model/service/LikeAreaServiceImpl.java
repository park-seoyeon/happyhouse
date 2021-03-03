package com.ssafy.happyhouse.model.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.LikeAreaDto;
import com.ssafy.happyhouse.model.dao.LikeAreaDao;

@Service
public class LikeAreaServiceImpl implements LikeAreaService {
	
	@Autowired
	private SqlSession session;

	@Override
	public LikeAreaDto select(String userid) {
		return session.getMapper(LikeAreaDao.class).select(userid);
	}

	@Override
	public int insert(LikeAreaDto likeAreaDto) {
		return session.getMapper(LikeAreaDao.class).insert(likeAreaDto);
	}

	@Override
	public int update(LikeAreaDto likeAreaDto) {
		return session.getMapper(LikeAreaDao.class).update(likeAreaDto);
	}

	@Override
	public int delete(String userid) {
		return session.getMapper(LikeAreaDao.class).delete(userid);
	}


}
