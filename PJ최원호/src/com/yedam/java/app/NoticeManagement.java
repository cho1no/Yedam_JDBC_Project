package com.yedam.java.app;

import java.util.List;

import com.yedam.java.board.Notice;
import com.yedam.java.board.NoticeDAO;
import com.yedam.java.common.Management;

public class NoticeManagement {
	// 필드
	private NoticeDAO ntDAO = null;
	// 생성자
	public NoticeManagement() {
		ntDAO = NoticeDAO.getInstance();
	}
	// 메소드
	public void run() {
		while(true) {
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
			}else if (menu == 5 && level == 9) { // 공지사항 삭제
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
			menu += "4.삭제 ";
			menu += "5.수정 ";
		}
		menu += "9.뒤로가기 ";

		System.out.println("==================================");
		System.out.println(menu);
		System.out.println("==================================");
	}
	
	// 공지사항 목록 보기
	private void showNoticeList() {
		List<Notice> list = ntDAO.selectNoticeAll();
		Management.showList(list);
	}
	// 공지사항 자세히 보기
	private void showNoticeDetail() {
		System.out.print("자세히 볼 게시글 번호 입력 > ");
		int ntKey = Management.inputNumber();
		Notice nt = ntDAO.selectNotice(ntKey);
		System.out.println(showDetail(nt));
	}
	private String showDetail(Notice nt) {
		String details = "";
		details += "제목 : " + nt.getNoticeTitle() + "\n";
		details += "내용 : " + nt.getNoticeDetail() + "\n";
		details += "글쓴이 : " + nt.getNoticeWriter() + " | 작성일 : " + nt.getNoticeWriteDay();
		return details;
	}
	// 삽입
	private void insertNotice() {}
	// 수정
	private void updateNotice() {}
	// 삭제
	private void deleteNotice() {}
}
