package com.ssafy.happyhouse.model.service;

import com.ssafy.happyhouse.model.LikeAreaDto;

public interface LikeAreaService {
	
	public LikeAreaDto select(String userid);
	public int insert(LikeAreaDto likeAreaDto);
	public int update(LikeAreaDto likeAreaDto);
	public int delete(String userid);
}
