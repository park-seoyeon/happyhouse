package com.ssafy.happyhouse.controller;

import java.util.HashMap;
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

import com.ssafy.happyhouse.model.MemberDto;
import com.ssafy.happyhouse.model.service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class HouseUserController {
	@Autowired
	private UserService  userservice;
	
	@PostMapping(value = "/register")
	protected String register(@RequestBody MemberDto memberDto) {
		System.out.println("REST register call");
		System.out.println(memberDto);
		try {
			userservice.add(memberDto);
			return "SUCCESS";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "FAIL";
		}
	}
	
	@GetMapping(value = "/view/{id}")
	public Map<String, Object> view(@PathVariable("id")String id) {
		System.out.println("REST view call, id : " + id);
		Map<String, Object> map = new HashMap<>();
		try {
			MemberDto memberDto = userservice.search(id);
			map.put("status", "SUCCESS");
			map.put("user", memberDto);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			map.put("status", "FAIL");
			map.put("data", e.getMessage());
		}
		return map;
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public String delete(@PathVariable("id") String id, HttpSession hs) {
		System.out.println("REST delete call, id : " + id);
		try {
			userservice.delete(id);
			hs.invalidate();
			return "SUCCESS";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "FAIL";
		}
	}
	
	@PutMapping(value = "/update")
	public Map<String, Object> update(@RequestBody MemberDto memberDto) {
		System.out.println("REST update call");
		System.out.println(memberDto);
		Map<String, Object> map = new HashMap<>();
		try {
			userservice.update(memberDto);
			MemberDto newMemberDto = userservice.search(memberDto.getUserid());
			map.put("status", "SUCCESS");
			map.put("user", newMemberDto);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			map.put("status", "FAIL");
			map.put("data", e.getMessage());
		}
		return map;
	}
}
