package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.CommentDto;

public interface CommentService {
	
	public List<CommentDto> search(int boardno);
	public int insert(CommentDto commentDto);
	public int delete(int no);
	public int deleteAll(int boardno);
}
