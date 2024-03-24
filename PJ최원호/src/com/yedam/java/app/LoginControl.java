package com.yedam.java.app;

import com.yedam.java.member.Member;

public class LoginControl {

	// 싱글톤
	private static Member loginInfo = null;
	public static Member getLoginInfo() {
		return loginInfo;
	}
	// 권한체크
	public static boolean selectLoginRole() {
		int memLevel = loginInfo.getMemLevel();
		if(memLevel == 9) {
			return true;
		} else {
			return false;
		}
	}
}
