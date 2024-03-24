package com.yedam.java.app;

import java.util.List;
import java.util.Scanner;

import com.yedam.java.books.Book;
import com.yedam.java.books.BookDAO;

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
		while (true) {
			// 메뉴 출력
			menuPrint();
			// 메뉴 선택
			int menu = selectMenu();
			// 메뉴 실행
			if (menu == 1) { // 전체조회
				selectBookAll();
			} else if (menu == 2) { // 도서검색
				searchBook();
			} else if (menu == 3) { // 도서등록
				insertBook();
			} else if (menu == 4) { // 도서수정
				updateBook();
			} else if (menu == 5) { // 도서삭제
				deleteBook();
			} else if (menu == 9) { // 종료
				end();
				break;
			} else {
				showError();
			}
		}
	}

	private void end() {
		System.out.println("프로그램이 종료되었습니다.");
	}

	private void showError() {
		System.out.println("메뉴에서 선택해주세요.");
	}

	private void menuPrint() {
		String menu = "";
		menu += "1.전체조회 ";
		menu += "2.도서검색 ";
		menu += "3.도서등록 ";
		menu += "4.도서수정 ";
		menu += "5.도서삭제 ";
		menu += "9.종료 ";

		System.out.println("===");
		System.out.println(menu);
		System.out.println("===");
	}

	private int selectMenu() {
		System.out.print("선택 > ");
		return inputNumber();
	}
	private int inputBNo() {
		System.out.print("도서번호 > ");
		return inputNumber();
	}
	private int inputNumber() {
		int num = 0;
		try {
			num = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("숫자로 입력해주시기 바랍니다.");
		}
		return num;
	}

	private void selectBookAll() {
		// 도서정보 전체조회
		List<Book> list = bookDAO.selectBookAll();
		// 결과관리
		// 성공 / 실패
		showBooks(list);
	}
	
	private void searchBook() {
		// 검색어 입력
		System.out.print("검색어 입력 > ");
		String search = sc.nextLine();
		// 단건조회
		List<Book> list = bookDAO.searchBook(search);
		// 결과관리
		showBooks(list);
	}
	
	private void showBooks(List<Book> list) {
		// 결과관리
		// 성공 / 실패
		if (list.isEmpty()) { // 실패
			System.out.println("데이터가 존재하지 않습니다.");
		} else { // 성공
			System.out.println("도서번호 | 도서이름 | 도서저자 | 소개 | 재고 | 등록일");
			for (Book b : list) {
				System.out.println(b);
			}
		}
	}
	private Book inputBook() {
		Book book = new Book();
		System.out.print("도서제목 > ");
		book.setbTitle(sc.nextLine());
		System.out.print("도서저자 > ");
		book.setbWriter(sc.nextLine());
		System.out.println("재고 > ");
		book.setbInventroy(inputNumber());
		return book;
	}
	
	private void insertBook() {
		// 전체 입력
		Book book = inputBook();
		
		// 정보 등록
		int result = bookDAO.insertBook(book);
		
		// 결과 처리
		if (result > 0) { // 성공
			System.out.println("정상적으로 등록되었습니다.");
		} else { // 실패
			System.out.println("정상적으로 등록되지 않았습니다.");
			System.out.println("정보를 확인해주세요.");
		}
	}
	
	private void updateBook() {
		// 수정 정보 입력
		Book book = changeBookInfo();
		
		// 정보 등록
		int result = bookDAO.updateBook(book);
		
		// 결과 처리
		if (result > 0) { // 성공
			System.out.println("정상적으로 등록되었습니다.");
		} else { // 실패
			System.out.println("정상적으로 등록되지 않았습니다.");
			System.out.println("정보를 확인해주세요.");
		}
	}
	
	private Book changeBookInfo() {
		Book book = new Book();

		book.setbNo(inputBNo());
		System.out.print("도서제목 > ");
		book.setbTitle(sc.nextLine());
		System.out.print("도서저자 > ");
		book.setbWriter(sc.nextLine());
		return book;
	}
	
	private void deleteBook() {
		// 삭제할 도서번호
		int bNo = inputBNo();
		
		// 추가 프로세스
		boolean isDelete = checkBookDel(bNo);
		if (!isDelete) return; 
		
		// 해당 도서 삭제
		int result = bookDAO.deleteBook(bNo);
		
		// 결과 처리
		if(result > 0) {
			System.out.println("정상적으로 삭제되었습니다.");
			System.out.println("도서번호 : " + bNo);
		}else {
			System.out.println("정상적으로 삭제되지 않았습니다.");
			System.out.println("정보를 확인해주세요.");
		}
	}
	
	private boolean checkBookDel(int bNo) {
		boolean isDel = false;
		// 정보 출력
		System.out.println("도서번호 | 도서이름 | 도서저자 | 소개 | 재고 | 등록일");
		System.out.println(bookDAO.selectBook(bNo));
		
		// 삭제여부 확인
		System.out.println("삭제를 진행하시겠습니까?(Y/N) > ");
		String result = sc.nextLine();
		if (result.equalsIgnoreCase("Y")) {
			isDel = true;
		}
		return isDel;
	}
}
