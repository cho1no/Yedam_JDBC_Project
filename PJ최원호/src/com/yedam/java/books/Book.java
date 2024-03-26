package com.yedam.java.books;

import java.sql.Date;

import com.yedam.java.common.Management;

// VO Class
public class Book {
	// 필드
	private int bNo; // 책번호
	private String bTitle; // 책제목
	private String bWriter; // 저자
	private String bInfo; // 소개
	private int bInventory; // 재고
	private Date bCreateDate; // 등록일
	
	// 생성자
	
	// 메소드
	// getter, setter
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
	public String getbInfo() {
		return bInfo;
	}
	public void setbInfo(String bInfo) {
		this.bInfo = bInfo;
	}
	public int getbInventory() {
		return bInventory;
	}
	public void setbInventory(int bInventory) {
		this.bInventory = bInventory;
	}
	public Date getbCreateDate() {
		return bCreateDate;
	}
	public void setbCreateDate(Date bCreateDate) {
		this.bCreateDate = bCreateDate;
	}
	
	@Override
	public String toString() {
		String text = "";
		text += Management.setLength(Integer.toString(bNo), Management.BOOK_ID_LEN) + " | ";
		text += Management.setLength(bTitle, Management.BOOK_TITLE_LEN) + "\t | ";
		text += Management.setLength(bWriter, Management.BOOK_WRITER_LEN) + "\t | ";
		text += Management.setLength(bInventory+"권", Management.BOOK_INVENTORY_LEN) + "\t | ";
		text += bCreateDate;
		return  text;
	}
	
}
