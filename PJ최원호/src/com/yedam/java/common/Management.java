package com.yedam.java.common;

import java.util.List;
import java.util.Scanner;

import com.yedam.java.board.Notice;
import com.yedam.java.books.Book;
import com.yedam.java.books.BookRent;

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

	public static <T> void showList(List<T> list) {
		// 결과관리
		// 성공 / 실패
		if (list.isEmpty()) { // 실패
			System.out.println("데이터가 존재하지 않습니다.");
		} else { // 성공
			String listName = "";
			if (list.get(0) instanceof Book) {
				listName = "도서번호 | 도서이름 | 도서저자 | 소개 | 재고 | 등록일";
			}
			if (list.get(0) instanceof BookRent) {
				listName = "대여번호 | 책번호 | 책이름 | 저자 | 빌린사람 | 대여일 | 반납일 | 반납여부";
			}
			if (list.get(0) instanceof Notice) {
				listName = "글번호 | 제목 | 작성자 | 작성일";
			}
			System.out.println(listName);
			for (T b : list) {
				System.out.println(b);
			}
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
	
	public static void dmlResult(int result, String dml) {
		if (result > 0) {
			System.out.println(dml + " 성공");
		} else {
			System.out.println(dml + " 실패");
		}
	}
}
