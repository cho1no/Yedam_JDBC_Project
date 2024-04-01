package com.yedam.java.board;

import java.sql.Date;

import com.yedam.java.common.Management;

public class Notice {
	// 필드
	private int noticeKey;
	private String noticeTitle;
	private String noticeDetail;
	private String noticeWriter;
	private Date noticeWriteDay;
	
	// 메소드
	// get, set
	public int getNoticeKey() {
		return noticeKey;
	}
	public void setNoticeKey(int noticeKey) {
		this.noticeKey = noticeKey;
	}
	public String getNoticeTitle() {
		return noticeTitle;
	}
	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	public String getNoticeDetail() {
		return noticeDetail;
	}
	public void setNoticeDetail(String noticeDetail) {
		this.noticeDetail = noticeDetail;
	}
	public String getNoticeWriter() {
		return noticeWriter;
	}
	public void setNoticeWriter(String noticeWriter) {
		this.noticeWriter = noticeWriter;
	}
	public Date getNoticeWriteDay() {
		return noticeWriteDay;
	}
	public void setNoticeWriteDay(Date noticeWriteDay) {
		this.noticeWriteDay = noticeWriteDay;
	}
	
	@Override
	public String toString() {
		String text = "";
		text += Management.setLength(Integer.toString(noticeKey), Management.KEY_LEN) + " | ";
		text += Management.setLength(noticeTitle, Management.BOOK_TITLE_LEN) + "\t | ";
		text += Management.setLength(noticeWriter, Management.USER_ID_LEN) + " | ";
		text += noticeWriteDay;
		return text;
	}
}
