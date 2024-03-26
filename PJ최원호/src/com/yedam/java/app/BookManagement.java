package com.yedam.java.app;

import java.util.List;
import java.util.Scanner;

import com.yedam.java.books.Book;
import com.yedam.java.books.BookDAO;
import com.yedam.java.common.Management;

public class BookManagement {
	// 필드
	private Scanner sc = null;
	private BookDAO bookDAO = null;

	// 생성자
	public BookManagement() {
		sc = new Scanner(System.in);
		bookDAO = BookDAO.getInstance();
	}

	// 메소드
	public void run() {
		int level = LoginControl.userLevel(); // 로그인 계급별 메뉴
		while (true) {
			// 메뉴 출력
			menuPrint(level);
			// 메뉴 선택
			int menu = Management.selectMenu();
			// 메뉴 실행
			if (menu == 1) { // 전체조회
				selectBookAll();
			} else if (menu == 2) { // 도서검색
				searchBook();
			} else if (menu == 3 && level >= 0) { // 도서대출
				new BookRentManagement().run();
			} else if (menu == 4 && level == 9) { // 도서등록
				insertBook();
			} else if (menu == 5 && level == 9) { // 도서수정
				updateBook();
			} else if (menu == 6 && level == 9) { // 도서삭제
				deleteBook();
			} else if (menu == 9) { // 탈출
				Management.exit();
				break;
			} else {
				Management.showError();
			}
		}
	}

	private void menuPrint(int level) {
		String menu = "";
		menu += "1.전체조회 ";
		menu += "2.도서검색 ";
		if (level >= 0)
			menu += "3.도서대여 ";
		if (level == 9) {
			menu += "4.도서등록 ";
			menu += "5.도서수정 ";
			menu += "6.도서삭제 ";
		}
		menu += "9.뒤로가기 ";

		System.out.println("==========================================");
		System.out.println(menu);
		System.out.println("==========================================");
	}

	private int inputBNo() {
		System.out.print("도서번호 > ");
		return Management.inputNumber();
	}

	private void selectBookAll() {
		// 도서정보 전체조회
		List<Book> list = bookDAO.selectBookAll();
		// 결과관리
		// 성공 / 실패
		Management.showListTitle("Book");
		Management.showList(list);
	}

	private void searchBook() {
		// 검색어 입력
		System.out.print("검색어 입력 > ");
		String search = sc.nextLine();
		// 단건조회
		List<Book> list = bookDAO.searchBook(search);
		// 결과관리
		Management.showListTitle("Book");
		Management.showList(list);
	}
	
	private Book inputBook() {
		Book book = new Book();
		System.out.print("도서제목 > ");
		book.setbTitle(sc.nextLine());
		System.out.print("도서저자 > ");
		book.setbWriter(sc.nextLine());
		System.out.print("도서소개 > ");
		book.setbInfo(sc.nextLine());
		System.out.print("재고 > ");
		book.setbInventory(Management.inputNumber());
		return book;
	}

	private void insertBook() {
		// 전체 입력
		Book book = inputBook();

		// 정보 등록
		int result = bookDAO.insertBook(book);

		// 결과처리
		Management.dmlResult(result, "도서 등록");
	}

	private void updateBook() {
		// 수정 정보 입력
		System.out.println("재고 입력 시 재고가 추가됩니다.");
		System.out.print("수정할 책 번호 입력 > ");
		int update = Management.inputNumber();
		Book book = inputBook();
		book.setbNo(update);

		// 정보 등록
		int result = bookDAO.updateBook(book);
		
		// 결과처리
		Management.dmlResult(result, "도서 수정");
	}

	private void deleteBook() {
		// 삭제할 도서번호
		int bNo = inputBNo();

		// 추가 프로세스
		boolean isDelete = checkBookDel(bNo);
		if (!isDelete)
			return;

		// 해당 도서 삭제
		int result = bookDAO.deleteBook(bNo);

		// 결과 처리
		Management.dmlResult(result, "도서 삭제");
	}

	private boolean checkBookDel(int bNo) {
		// 정보 출력
		System.out.println("도서번호 | 도서이름 | 도서저자 | 소개 | 재고 | 등록일");
		System.out.println(bookDAO.selectBook(bNo));

		return Management.checkDel();
	}
}
