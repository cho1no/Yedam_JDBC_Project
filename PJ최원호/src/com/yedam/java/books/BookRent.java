package com.yedam.java.books;

import java.sql.Date;

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
	private int isReturn; // 반납여부 : 0 -> 미반납, 1 -> 반납

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
		return  rentKey + " | " + bNo + " | " + bTitle + " | " + bWriter + " | " + renter
				+ " | " + start + " | " + end + " | " + ((isReturn == 0) ? "대여중" : "반납완료");
	}

}
