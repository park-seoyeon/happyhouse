package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.CommentDto;
import com.ssafy.happyhouse.model.dao.CommentDao;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private SqlSession session;

	@Override
	public List<CommentDto> search(int boardno) {
		return session.getMapper(CommentDao.class).search(boardno);
	}

	@Override
	public int insert(CommentDto commentDto) {
		return session.getMapper(CommentDao.class).insert(commentDto);
	}

	@Override
	public int delete(int no) {
		return session.getMapper(CommentDao.class).delete(no);
	}

	@Override
	public int deleteAll(int boardno) {
		return session.getMapper(CommentDao.class).deleteAll(boardno);
	}
	

}
