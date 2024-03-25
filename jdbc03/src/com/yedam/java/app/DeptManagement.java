package com.yedam.java.app;

import java.util.List;
import java.util.Scanner;

import com.yedam.java.dept.Department;
import com.yedam.java.dept.DeptDAO;

public class DeptManagement {
	// 필드
	private Scanner sc = null;
	private DeptDAO deptDAO = null;

	// 생성자
	public DeptManagement() {
		sc = new Scanner(System.in);
		deptDAO = DeptDAO.getInstance();
	}

	// 메소드
	public void run() {
		while (true) {
			menuPrint();

			int menuNo = menuSelect();

			if (menuNo == 1) {
				insertDept();
			} else if (menuNo == 2) {
				updateDept();
			} else if (menuNo == 3) {
				deleteDept();
			} else if (menuNo == 4) {
				selectDeptInfo();
			} else if (menuNo == 5) {
				selectDeptAll();
			} else if (menuNo == 9) {
				end();
				break;
			} else {
				showError();
			}
		}
		sc.close();
	}

	private void end() {
		System.out.println("프로그램을 종료합니다.");
	}

	private void showError() {
		System.out.println("메뉴에서 선택해주시기 바랍니다.");
	}

	private void menuPrint() {
		String menu = "";
		menu += "1.부서등록 ";
		menu += "2.부서수정 ";
		menu += "3.부서삭제 ";
		menu += "4.부서조회 ";
		menu += "5.부서전체조회 ";
		menu += "9.종료";
		System.out.println("===");
		System.out.println(menu);
		System.out.println("===");
	}

	private int menuSelect() {
		System.out.print("선택>> ");
		return inputNumber();
	}

	private void insertDept() {
		// 1) 삽입하고자 하는 부서 정보 입력
		Department dept = inputDeptAll();
		// 2) 해당정보로 업데이트 진행
		int result = deptDAO.insertDeptInfo(dept);
		// 3) 결과 처리(success / fail)
		if (result < 1) {
			// 3-1) fail : 실패 메세지 출력
			System.out.println("삽입에 실패했습니다.");
			System.out.println("정보를 확인해주세요.");
		} else {
			// 3-2) success : 성공 메세지 출력
			System.out.println("성공적으로 삽입되었습니다.");
		}
	}

	private void updateDept() {
		// 1) 수정하고자 하는 정보 입력 : 부서번호, 부서이름
		Department dept = inputDeptInfo();
		// 2) 해당 정보로 업데이트 진행
		int result = deptDAO.updateDeptInfo(dept);
		// 3) 결과 처리(success / fail)
		if (result < 1) {
			// 3-1) fail : 실패 메세지 출력
			System.out.println("수정에 실패했습니다.");
			System.out.println("정보를 확인해주세요.");
		} else {
			// 3-2) success : 성공 메세지 출력
			System.out.println("성공적으로 수정되었습니다.");
		}
	}

	private void deleteDept() {
		// 1) 삭제하고자하는 부서번호 입력
		int deptId = inputDeptId();
		// 추가 프로세스
		boolean isDeleted = checkDeptDelete(deptId);
		
		if(!isDeleted) return;
		
		// 2) 삭제 진행
		int result = deptDAO.deleteDeptInfo(deptId);
		// 3) 결과처리
		if (result < 1) {
			// 3-1) fail : 실패 메세지 출력
			System.out.println("삭제에 실패했습니다.");
			System.out.println("정보를 확인해주세요.");
		} else {
			// 3-2) success : 성공 메세지 출력
			System.out.println("성공적으로 삭제되었습니다.");
			System.out.println("부서번호 : " + deptId);
		}
	}

	private void selectDeptInfo() {
		// 1) 보고자하는 부서번호 입력
		int deptId = inputDeptId();
		// 2) 해당 부서번호로 단건조회
		Department dept = deptDAO.selectDeptInfo(deptId);
		// 3) 결과처리
		if (dept == null) {
			// 3-1) fail : 별도 메세지 출력
			System.out.println("해당하는 부서가 없습니다.");
		} else {
			// 3-2) success : 출력
			System.out.println("부서번호\t| 부서이름\t| 부서장\t| 위치번호");
			System.out.println(dept);
		}
	}

	private void selectDeptAll() {
		// 1. 부서정보 전체조회
		List<Department> list = deptDAO.selectDeptAll();
		// 2. 결과처리
		if (list.isEmpty()) {
			// 2-1) fail : 별도 메세지 출력
			System.out.println("데이터가 존재하지 않습니다.");
		} else {
			// 2-2) Success : 전체출력
			System.out.println("부서번호\t| 부서이름\t| 부서장\t| 위치번호");
			for (Department data : list) {
				System.out.println(data);
			}
		}
	}
	
	private Department inputDeptAll() {
		Department dept = new Department();
		System.out.print("부서번호 > ");
		dept.setDepartmentId(Integer.parseInt(sc.nextLine()));
		System.out.print("부서이름 > ");
		dept.setDepartmentName(sc.nextLine());
		System.out.print("부서장 번호 > ");
		dept.setManagerId(Integer.parseInt(sc.nextLine()));
		System.out.print("부서 위치 번호 > ");
		dept.setLocationId(Integer.parseInt(sc.nextLine()));
		
		return dept;
	}
	
	private Department inputDeptInfo() {
		System.out.print("부서번호 > ");
		int deptId = Integer.parseInt(sc.nextLine());
		System.out.print("부서이름 > ");
		String deptName = sc.nextLine();
		
		Department dept = new Department();
		dept.setDepartmentId(deptId);
		dept.setDepartmentName(deptName);
		
		return dept;
	}
	
	private boolean checkDeptDelete(int deptId) {
		// 추가1) 삭제하고자 하는 부서 정보 출력
		Department dept = deptDAO.selectDeptInfo(deptId);
		
		System.out.println("부서번호\t| 부서이름\t| 부서장\t| 위치번호");
		System.out.println(dept);
		// 추가2) 다시 한번 삭제여부를 확인
		boolean isSelected = false;
		System.out.print("삭제를 진행하시겠습니까?(Y/N) > ");
		String result = sc.nextLine();
		if (result.equalsIgnoreCase("Y")) {
			isSelected = true;
		}
		return isSelected;
	}
	
	private int inputDeptId() {
		System.out.print("부서번호 > ");
		return inputNumber();
	}

	private int inputNumber() {
		int number = 0;
		try {
			number = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("숫자를 입력해주시기 바랍니다.");
		}
		return number;
	}
}
