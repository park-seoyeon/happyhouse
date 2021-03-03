package com.ssafy.happyhouse.model;

import java.util.Date;

public class CommentDto {
	private int no;
	private int boardno;
	private String writer;
	private String content;
	private Date regtime;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getBoardno() {
		return boardno;
	}

	public void setBoardno(int boardno) {
		this.boardno = boardno;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getRegtime() {
		return regtime;
	}

	public void setRegtime(Date regtime) {
		this.regtime = regtime;
	}

	@Override
	public String toString() {
		return "CommentDto [no=" + no + ", boardno=" + boardno + ", writer=" + writer + ", content=" + content
				+ ", regtime=" + regtime + "]";
	}

}