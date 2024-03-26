package com.yedam.java.app;

import java.util.Scanner;

import com.yedam.java.common.Management;
import com.yedam.java.member.Member;
import com.yedam.java.member.MemberDAO;

public class LoginControl {
	private Scanner sc = new Scanner(System.in);
	// 싱글톤
	private static Member loginInfo = null;

	public static Member getLoginInfo() {
		return loginInfo;
	}

	// 권한체크
	public static int userLevel() {
		int memLevel = loginInfo.getMemLevel();
		return memLevel;
	}

	public void run() {
		Management.showWelcome();
		while (true) {
			menuPrint();
			int menuNo = Management.selectMenu();
			if (menuNo == 1) { // 로그인
				login();
			} else if (menuNo == 2) { // 회원가입
				regist();
			} else if (menuNo == 9) { // 종료
				Management.end();
				break;
			} else {
				Management.showError();
			}
		}
	}

	private void menuPrint() {
		System.out.println("========================");
		System.out.println("1.로그인 2.회원가입 9.종료");
		System.out.println("========================");
	}

	private void regist() {
		System.out.println("==========회원가입==========");
		// input id, pw
		Member inputInfo = inputMember();
		inputTel(inputInfo);
		int result = MemberDAO.getInstance().newMember(inputInfo);

		// result print
		if (result > 0) { // succ
			System.out.println("성공적으로 가입되었습니다.");
		} else { // fail
			System.out.println("정상적으로 가입되지 않았습니다.");
			System.out.println("아이디가 중복이거나 이미 가입된 전화번호입니다.");
		}
	}

	private void login() {
		System.out.println("==========로그인==========");
		// 아이디와 비밀번호 입력
		Member inputInfo = inputMember();
		// 로그인 시도
		loginInfo = MemberDAO.getInstance().selectOne(inputInfo);

		// 실패할 경우 그대로 메소드 종료
		if (loginInfo == null)
			return;

		// 성공할 경우 프로그램을 실행
		new BookManagement().run();
	}

	private Member inputMember() {
		Member info = new Member();
		System.out.print("아이디 > ");
		info.setMemId(sc.nextLine());
		System.out.print("비밀번호 > ");
		info.setMemPw(sc.nextLine());

		return info;
	}

	private Member inputTel(Member mem) {
		System.out.print("전화번호 > ");
		mem.setMemTel(sc.nextLine());
		return mem;
	}
}
