package com.yedam.java.common;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Scanner;

public final class Management {
	static Scanner sc = new Scanner(System.in);

	public static void showWelcome() {
		System.out.println("도서대출시스템에 오신것을 환영합니다.");
	}

	public static void exit() {
		System.out.println("이전 메뉴로 돌아갑니다.");
	}

	public static void end() {
		System.out.println("프로그램이 종료되었습니다.");
	}

	public static void showError() {
		System.out.println("메뉴를 확인해주시기 바랍니다.");
	}

	public static int selectMenu() {
		System.out.print("선택 > ");
		return inputNumber();
	}

	public static int inputNumber() {
		int num = 0;
		try {
			num = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("숫자로 입력해주시기 바랍니다.");
		}
		return num;
	}
	
	public static String setLength(String str, int length){
		String formatter = String.format("%%%ds", (length - getKorCnt(str)) * -1);
		return String.format(formatter, str);
	}
	private static int getKorCnt(String kor) {
	    int cnt = 0;
	    for (int i = 0 ; i < kor.length() ; i++) {
	        if (kor.charAt(i) >= '가' && kor.charAt(i) <= '힣') {
	            cnt++;
	        }
	    } return cnt;
	}
	
	public static <T> void showList(List<T> list) {
		// 결과관리
		// 성공 / 실패
		if (list.isEmpty()) { // 실패
			System.out.println("데이터가 존재하지 않습니다.");
		} else { // 성공
			for (T b : list) {
				System.out.println(b);
			}
		}
	}
	public static void showListTitle(String title) {
		String listTitle = "";
		if (title == "Book") {
			listTitle = "도서번호" + " | "
					  + setLength("도서이름",30) + "\t | "
					  + setLength("도서저자",20) + "\t | "
//					  + "소개" + " | "
					  + "재고" + " | "
					  + "등록일";
		} else if (title == "BookRent") {
			listTitle = "대여번호 | 책번호 | 책이름 | 저자 | 빌린사람 | 대여일 | 반납일 | 반납여부";
		} else if (title == "Notice") {
			listTitle = setLength("글번호", 5) +  " | "
					  + setLength("제목", 30) + "\t | "
					  + setLength("작성자", 11) + " | "
					  + "작성일";
		} else if (title == "ReviewGroup") {
			listTitle = "책번호 | "
					  + "책제목 | "
					  + "작성자 | "
					  + "평점";
		} else if (title == "ReviewBook") {
			listTitle = "대여번호 | "
					  + "책제목 | "
					  + "한줄평 | "
					  + "평점 | "
					  + "작성자 | "
					  + "작성일";
		} else {
			listTitle = "===";
		}
		System.out.println(listTitle);
	}
	public static void dmlResult(int result, String dml) {
		if (result > 0) {
			System.out.println(dml + " 성공");
		} else {
			System.out.println(dml + " 실패");
		}
	}

	public static boolean checkDel() {
		boolean isDel = false;
		// 삭제여부 확인
		System.out.print("삭제를 진행하시겠습니까?(Y/N) > ");
		String result = sc.nextLine();
		if (result.equalsIgnoreCase("Y")) {
			isDel = true;
		}
		return isDel;
	}
	
}
