package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.LikeAreaDto;
import com.ssafy.happyhouse.model.MemberDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.service.HouseMapService;
import com.ssafy.happyhouse.model.service.LikeAreaService;

@CrossOrigin("*")
@RestController
@RequestMapping("/likearea")
public class LikeAreaController {
	
	@Autowired
	private LikeAreaService likeAreaService;
	
	@GetMapping(value = "select/{userid}")
	public Map<String, Object> select(@PathVariable("userid") String userid) {
		Map<String, Object> map = new HashMap<>();
		System.out.println("REST likearea/select");
		try {
			LikeAreaDto likeArea = likeAreaService.select(userid);
			map.put("status", "SUCCESS");
			map.put("data", likeArea);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			map.put("status", "FAIL");
			map.put("data", e.getMessage());
		}
		return map;		
	}
	
	@PostMapping(value = "/register")
	public String insert(@RequestBody LikeAreaDto likeAreaDto) {
		System.out.println("REST likearea/register");
		System.out.println(likeAreaDto);
		Map<String, Object> map = new HashMap<>();
		try {
			likeAreaService.insert(likeAreaDto);
			return "SUCCESS";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "FAIL";
		}
	}
	
	@DeleteMapping(value = "/delete/{userid}")
	public String delete(@PathVariable("userid") String userid) {
		System.out.println("REST likearea/delete/" + userid);
		try {
			likeAreaService.delete(userid);
			return "SUCCESS";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "FAIL";
		}
	}
	
	@PutMapping(value = "/update")
	public String update(@RequestBody LikeAreaDto likeAreaDto) {
		System.out.println("REST likearea/update");
		System.out.println(likeAreaDto);
		try {
			likeAreaService.update(likeAreaDto);
			return "SUCCESS";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "FAIL";
		}
	}

}