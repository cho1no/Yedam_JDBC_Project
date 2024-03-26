package com.yedam.java.board;

import java.sql.Date;

import com.yedam.java.common.Management;

public class Review {
	// 필드
	private int reKey;
	private int bNo;
	private String bTitle;
	private String bWriter;
	private String reDetail;
	private int reRate;
	private String reWriter;
	private Date reWriteDay;

	// get, set
	public int getReKey() {
		return reKey;
	}

	public void setReKey(int reKey) {
		this.reKey = reKey;
	}

	public int getbNo() {
		return bNo;
	}

	public void setbNo(int bNo) {
		this.bNo = bNo;
	}

	public String getbTitle() {
		return bTitle;
	}

	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}

	public String getbWriter() {
		return bWriter;
	}

	public void setbWriter(String bWriter) {
		this.bWriter = bWriter;
	}

	public String getReDetail() {
		return reDetail;
	}

	public void setReDetail(String reDetail) {
		this.reDetail = reDetail;
	}

	public int getReRate() {
		return reRate;
	}

	public void setReRate(int reRate) {
		this.reRate = reRate;
	}

	public String getReWriter() {
		return reWriter;
	}

	public void setReWriter(String reWriter) {
		this.reWriter = reWriter;
	}

	public Date getReWriteDay() {
		return reWriteDay;
	}

	public void setReWriteDay(Date reWriteDay) {
		this.reWriteDay = reWriteDay;
	}

	// toString
	@Override
	public String toString() {
		String text = "";
		if (reKey != 0)
			text +=  Management.setLength(Integer.toString(reKey), Management.KEY_LEN) + " | ";
		if (bNo != 0)
			text += Management.setLength(Integer.toString(bNo), Management.BOOK_ID_LEN) + " | ";
		if (bTitle != null)
			text += Management.setLength(bTitle, Management.BOOK_TITLE_LEN) + "\t | ";
		if (bWriter != null) {
			text += Management.setLength(bWriter, Management.BOOK_WRITER_LEN) + "\t | ";
		}
		if (reDetail != null)
			text += Management.setLength(reDetail, Management.BOOK_TITLE_LEN+1) + "\t | ";
		// 별그리기
		for (int i = 0; i < reRate; i++) {
			text += "★";
		}
		for (int i = 0; i < 5-reRate; i++) {
			text += "☆";
		}
		if (reWriter != null)
			text += " | " + Management.setLength(reWriter, Management.USER_ID_LEN) + " | ";
		if (reWriteDay != null)
			text += reWriteDay;
		return text;
	}
}
