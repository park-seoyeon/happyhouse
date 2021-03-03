package com.ssafy.happyhouse.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.CommentDto;
@Mapper
public interface CommentDao {
	public List<CommentDto> search(int boardno);
	public int insert(CommentDto commentDto);
	public int delete(int no);
	public int deleteAll(int boardno);
}