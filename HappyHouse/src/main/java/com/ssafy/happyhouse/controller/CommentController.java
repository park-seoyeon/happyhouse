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

import com.ssafy.happyhouse.model.CommentDto;
import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.LikeAreaDto;
import com.ssafy.happyhouse.model.MemberDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.service.CommentService;
import com.ssafy.happyhouse.model.service.HouseMapService;
import com.ssafy.happyhouse.model.service.LikeAreaService;

@CrossOrigin("*")
@RestController
@RequestMapping("/comment")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@GetMapping(value = "/search/{boardno}")
	public Map<String, Object> select(@PathVariable("boardno") int boardno) {
		Map<String, Object> map = new HashMap<>();
		System.out.println("REST comment/search");
		try {
			List<CommentDto> list = commentService.search(boardno);
			map.put("status", "SUCCESS");
			map.put("data", list);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			map.put("status", "FAIL");
			map.put("data", e.getMessage());
		}
		return map;		
	}
	
	@PostMapping(value = "/insert")
	public String insert(@RequestBody CommentDto commentDto) {
		System.out.println("REST comment/insert");
		System.out.println(commentDto);
		Map<String, Object> map = new HashMap<>();
		try {
			commentService.insert(commentDto);
			return "SUCCESS";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "FAIL";
		}
	}
	
	@DeleteMapping(value = "/delete/{no}")
	public String delete(@PathVariable("no") int no) {
		System.out.println("REST comment/delete/" + no);
		try {
			commentService.delete(no);
			return "SUCCESS";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "FAIL";
		}
	}
	
	@DeleteMapping(value = "/deleteAll/{boardno}")
	public String deleteAll(@PathVariable("boardno") int boardno) {
		System.out.println("REST comment/deleteAll/" + boardno);
		try {
			commentService.deleteAll(boardno);
			return "SUCCESS";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "FAIL";
		}
	}
	

}