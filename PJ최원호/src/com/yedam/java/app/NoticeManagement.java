package com.yedam.java.app;

import java.util.List;
import java.util.Scanner;

import com.yedam.java.board.Notice;
import com.yedam.java.board.NoticeDAO;
import com.yedam.java.common.Management;

public class NoticeManagement {
	// 필드
	private NoticeDAO ntDAO = null;
	private Scanner sc = null;

	// 생성자
	public NoticeManagement() {
		ntDAO = NoticeDAO.getInstance();
		sc = new Scanner(System.in);
	}

	// 메소드
	public void run() {
		while (true) {
			int level = LoginControl.userLevel(); // 로그인 계급별 메뉴
			// 메뉴출력
			menuPrint(level);
			// 메뉴선택
			int menu = Management.selectMenu();
			// 메뉴실행
			if (menu == 1) { // 공지사항 목록 보기
				showNoticeList();
			} else if (menu == 2) { // 공지사항 자세히 보기
				showNoticeDetail();
			} else if (menu == 3 && level == 9) { // 공지사항 추가
				insertNotice();
			} else if (menu == 4 && level == 9) { // 공지사항 수정
				updateNotice();
			} else if (menu == 5 && level == 9) { // 공지사항 삭제
				deleteNotice();
			} else if (menu == 9) { // 탈출
				Management.exit();
				break;
			} else { // 오류
				Management.showError();
			}
		}
	}

	private void menuPrint(int level) {
		String menu = "";
		menu += "1.목록보기 ";
		menu += "2.내용보기 ";
		if (level == 9) {
			menu += "3.추가 ";
			menu += "4.수정 ";
			menu += "5.삭제 ";
		}
		menu += "9.뒤로가기 ";

		System.out.println("================= menu =================");
		System.out.println(menu);
		System.out.println("========================================");
	}

	// 공지사항 목록 보기
	private void showNoticeList() {
		List<Notice> list = ntDAO.selectNoticeAll();
		Management.showListTitle("Notice");
		Management.showList(list);
	}

	// 공지사항 자세히 보기
	private void showNoticeDetail() {
		System.out.print("자세히 볼 글 번호 입력 > ");
		int ntKey = Management.inputNumber();
		Notice nt = ntDAO.selectNotice(ntKey);
		if (nt == null) {
			System.out.println("글 번호가 잘못되었습니다.");
		} else {
			System.out.println(showDetail(nt));
		}
	}

	private String showDetail(Notice nt) {
		String details = "";
		details += "-----------------------------------\n";
		details += "제목 : " + nt.getNoticeTitle() + "\n";
		details += "내용 : " + nt.getNoticeDetail() + "\n";
		details += "글쓴이 : " + nt.getNoticeWriter() + " | 작성일 : " + nt.getNoticeWriteDay() + "\n";
		details += "-----------------------------------";
		return details;
	}

	private Notice inputNotice() {
		Notice nt = new Notice();
		System.out.print("제목 > ");
		nt.setNoticeTitle(sc.nextLine());
		System.out.print("내용 > ");
		nt.setNoticeDetail(sc.nextLine());
		return nt;
	}

	// 삽입
	private void insertNotice() {
		Notice nt = inputNotice();
		int result = ntDAO.insertNotice(nt);
		Management.dmlResult(result, "공지사항 추가");
	}

	// 수정
	private void updateNotice() {
		System.out.print("수정할 글 번호 > ");
		int ntKey = Management.inputNumber();
		Notice nt = inputNotice();
		nt.setNoticeKey(ntKey);
		int result = ntDAO.updateNotice(nt);
		Management.dmlResult(result, "공지사항 수정");
	}

	// 삭제
	private void deleteNotice() {
		System.out.print("삭제할 글 번호 > ");
		int delKey = Management.inputNumber();

		// 추가프로세스
		if (!checkNoticeDel(delKey))
			return;

		int result = ntDAO.deleteNotice(delKey);
		Management.dmlResult(result, "공지사항 삭제");
	}

	private boolean checkNoticeDel(int key) {
		// 삭제하고자 하는 게시글 정보 출력
		Notice nt = ntDAO.selectNotice(key);
		System.out.println(showDetail(nt));

		return Management.checkDel();
	}
}
