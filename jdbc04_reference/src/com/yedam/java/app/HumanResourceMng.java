package com.yedam.java.app;

import java.util.Scanner;

public class HumanResourceMng {
	// 필드
	private Scanner sc = null;

	// 생성자
	public HumanResourceMng() {
		sc = new Scanner(System.in);
	}

	public void run() {
		while (true) {

			// 메뉴출력
			menuPrint();

			// 메뉴선택
			int menu = selectMenu();

			if (menu == 1) {
				new EmpManagement().run();
			} else if (menu == 2) {
				new DeptManagement().run();
			}else if(menu == 9){
				end();
				break;
			}else {
				showError();
			}			
		}

	}
	
	private void showError() {
		System.out.println("메뉴에서 선택해주시기 바랍니다.");
	}
	
	private void end() {
		System.out.println("로그인 화면으로 돌아갑니다.");
	}
	
	private void menuPrint() {
		String menu = "";
		menu += "1.사원관리 ";
		menu += "2.부서관리 ";
		menu += "9.로그아웃 ";
		
		System.out.println("===");
		System.out.println(menu);
		System.out.println("===");
	}
	
	private int selectMenu() {
		System.out.print("선택 > ");
		return inputNumber();		
	}
	
	private int inputNumber() {
		int num = 0;
		try {
			num = Integer.parseInt(sc.nextLine());
		}catch(NumberFormatException e) {
			System.out.println("숫자로 입력해주시기 바랍니다.");
		}
		return num;
	}
}
