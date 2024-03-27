package com.yedam.java.app;

import com.yedam.java.common.Management;

public class BoardManagement {
	public void run() {
		while (true) {

			// 메뉴출력
			menuPrint();

			// 메뉴선택
			int menu = Management.selectMenu();

			if (menu == 1) {
				new ReviewManagement().run();
			} else if (menu == 2) {
				new NoticeManagement().run();
			}else if(menu == 9){
				Management.exit();
				break;
			}else {
				Management.showError();
			}			
		}
	}
	private void menuPrint() {
		String menu = "";
		menu += "1.후기게시판 ";
		menu += "2.공지사항 ";
		menu += "9.뒤로가기 ";
		
		System.out.println("===");
		System.out.println(menu);
		System.out.println("===");
	}
}
