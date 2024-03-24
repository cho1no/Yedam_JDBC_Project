package com.yedam.java.member;

public class Member {
	// 필드
	private String memId; // id
	private String memPw; // pw
	private int memLevel; // 0~8 : 일반, 9 : 관리자
	
	//메소드
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemPw() {
		return memPw;
	}
	public void setMemPw(String memPw) {
		this.memPw = memPw;
	}
	public int getMemLevel() {
		return memLevel;
	}
	public void setMemLevel(int memLevel) {
		this.memLevel = memLevel;
	}
	@Override
	public String toString() {
		String info = "";
		
		if (memLevel == 9) {
			info = "관리자 : ";
		} else {
			info = "일반 : ";
		}
		
		return info + memId;
	}
	
}
