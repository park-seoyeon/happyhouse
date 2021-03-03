package com.ssafy.happyhouse.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.LikeAreaDto;
@Mapper
public interface LikeAreaDao {
	public LikeAreaDto select(String userid);
	public int insert(LikeAreaDto likeAreaDto);
	public int update(LikeAreaDto likeAreaDto);
	public int delete(String userid);
}