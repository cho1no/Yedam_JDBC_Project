package com.yedam.java.books;

import java.sql.Date;

import com.yedam.java.common.Management;

//VO CLASS
public class BookRent {
	// 필드
	private int rentKey;
	private int bNo;
	private String bTitle;
	private String bWriter;
	private String renter;
	private Date start;
	private Date end;
	private int isReturn; // 반납여부 : -1 -> 연체, 0 -> 미반납, 1 -> 반납

	// 메소드
	// get, set
	public int getRentKey() {
		return rentKey;
	}

	public void setRentKey(int rentKey) {
		this.rentKey = rentKey;
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

	public String getRenter() {
		return renter;
	}

	public void setRenter(String renter) {
		this.renter = renter;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public int getIsReturn() {
		return isReturn;
	}

	public void setIsReturn(int isReturn) {
		this.isReturn = isReturn;
	}
	
	// toString
	@Override
	public String toString() {
		String text = "";
		text += Management.setLength(Integer.toString(rentKey), Management.KEY_LEN) + " | ";
		text += Management.setLength(Integer.toString(bNo), Management.BOOK_ID_LEN) + " | ";
		text += Management.setLength(bTitle, Management.BOOK_TITLE_LEN) + "\t | ";
		text += Management.setLength(bWriter, Management.BOOK_WRITER_LEN) + "\t | ";
		text += Management.setLength(renter, Management.USER_ID_LEN) + "\t | ";
		text += start + " | ";
		text += end + " | ";
		if (isReturn == 2) text += "연체";
		else if (isReturn == 1) text += "반납완료";
		else text += "대여중"; 
//		text += ((isReturn == 0) ? "대여중" : "반납완료");
		return text;
	}

}
