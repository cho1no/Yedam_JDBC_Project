package com.yedam.java.app;

import java.sql.Date;
import java.util.List;

import com.yedam.java.books.Book;
import com.yedam.java.books.BookDAO;
import com.yedam.java.books.BookRent;
import com.yedam.java.books.BookRentDAO;
import com.yedam.java.common.Management;

public class BookRentManagement {
	// 필드
	private BookRentDAO brDAO = null;
	private BookDAO bookDAO = null;

	// 생성자
	public BookRentManagement() {
		brDAO = BookRentDAO.getInstance();
		bookDAO = BookDAO.getInstance();
		updateOverRent();
	}

	public void run() {
		while (true) {
			// 메뉴출력
			menuPrint();
			// 메뉴선택
			int menu = Management.selectMenu();
			// 메뉴실행
			if (menu == 1) { // 대여 기록 확인
				showRentalList(true);
			} else if (menu == 2) { // 대여
				rentBook();
			} else if (menu == 3) { // 반납
				returnBook();
			} else if (menu == 9) { // 탈출
				Management.exit();
				break;
			} else { // 오류
				Management.showError();
			}
		}
	}

	private void menuPrint() {
		System.out.println("================================");
		System.out.println("1.대여기록 2.대여 3.반납 9.뒤로가기");
		System.out.println("================================");
	}
	
	// 반납기록 전체조회
	private void showRentalList(boolean isAll) {
		List<BookRent> list = brDAO.selectRentBook(isAll);
		Management.showListTitle("BookRent");
		Management.showList(list);
	}
	
	// 대여
	private void rentBook() {
		List<Book> list = bookDAO.selectBookAll();
		// 대여할 book list 보여주기
		Management.showListTitle("Book");
		Management.showList(list);
		System.out.print("대여할 책 번호 입력 > ");
		int select = Management.inputNumber();
		int result = brDAO.newRent(select);
		Management.dmlResult(result, "도서 대여");
	}

	// 반납
	private void returnBook() {
		System.out.println("===============미반납 도서 목록===============");
		showRentalList(false);
		System.out.print("반납할 대여번호 입력 > ");
		int select = Management.inputNumber();
		int result = brDAO.updateRented(select);
		Management.dmlResult(result, "도서 반납");
	}
	// 연체정보 업데이트
	private void updateOverRent() {
		List<BookRent> list = brDAO.selectRentBook();
		Date date = new Date(System.currentTimeMillis());
		for (BookRent br : list) {
			if (date.compareTo(br.getEnd()) > 0) {
				brDAO.updateOverRent(br.getRentKey());
			}
		}
	}
}
