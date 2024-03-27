package com.yedam.java.app;

import java.util.List;
import java.util.Scanner;

import com.yedam.java.board.Review;
import com.yedam.java.board.ReviewDAO;
import com.yedam.java.common.Management;

public class ReviewManagement {
	// 필드
	private ReviewDAO reDAO = null;
	private Scanner sc = null;
	
	// 생성자
	public ReviewManagement() {
		reDAO = ReviewDAO.getInstance();
		sc = new Scanner(System.in);
	}
	
	// 메소드
	public void run() {
		while (true) {
			// 메뉴출력
			menuPrint();
			// 메뉴선택
			int menu = Management.selectMenu();
			// 메뉴실행
			if (menu == 1) { // 책별 리뷰 목록 보기
				showReviewList();
			} else if (menu == 2) { // 책 리뷰 모음 보기
				showReviewBook();
			} else if (menu == 3) { // 리뷰 추가
				insertReview();
			} else if (menu == 4) { // 리뷰 수정
				updateReview();
			} else if (menu == 5) { // 리뷰 삭제
				deleteReview();
			} else if (menu == 9) { // 탈출
				Management.exit();
				break;
			} else { // 오류
				Management.showError();
			}
		}
	}
	
	private void menuPrint() {
		String menu = "";
		menu += "1.평가보기 ";
		menu += "2.리뷰보기 ";
		menu += "3.리뷰추가 ";
		menu += "4.리뷰수정 ";
		menu += "5.리뷰삭제 ";
		menu += "9.뒤로가기 ";

		System.out.println("======================================");
		System.out.println(menu);
		System.out.println("======================================");
	}
	
	// 책별 리뷰 목록
	private void showReviewList() {
		List<Review> list = reDAO.selectReviewAll();
		Management.showListTitle("ReviewGroup");
		Management.showList(list);
	}
	
	// 책 리뷰 모음
	private void showReviewBook() {
		List<Review> list = reDAO.selectReviewGroup(selectBookNum());
		Management.showListTitle("ReviewBook");
		Management.showList(list);
	}
	private int selectBookNum() {
		System.out.print("책번호 > ");
		return Management.inputNumber();
	}
	private int selectReviewKey() {
		System.out.print("리뷰번호 > ");
		return Management.inputNumber();
	}
	private Review inputReview() {
		Review re = new Review();
		re.setbNo(selectBookNum());
		System.out.print("내용 > ");
		re.setReDetail(sc.nextLine());
		System.out.println("평점 > ");
		int rate = Management.inputNumber();
		if (rate > 5) rate = 5;
		if (rate < 0) rate = 0;
		re.setReRate(rate);
		return re;
	}
	// 리뷰 추가
	private void insertReview() {
		int result = reDAO.insertReview(inputReview());
		Management.dmlResult(result, "리뷰 추가");
	}
	
	// 리뷰 수정
	private void updateReview() {
		int result = reDAO.updateReview(inputReview());
		Management.dmlResult(result, "리뷰 수정");
	}
	
	// 리뷰 수정
	private void deleteReview() {
		Review re = new Review();
		int uLevel = LoginControl.userLevel();
		int result = 0;
		if (uLevel == 9) {
			re.setReKey(selectReviewKey());
		} else {
			re.setbNo(selectBookNum());
		}
		
		if (!Management.checkDel())
			return;
		
		result = reDAO.deleteReview(re);
		Management.dmlResult(result, "리뷰 삭제");
	}
}
