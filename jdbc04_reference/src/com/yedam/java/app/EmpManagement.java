package com.yedam.java.app;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;


import com.yedam.java.emp.EmpDAO;
import com.yedam.java.emp.Employee;

public class EmpManagement {
	//필드
	private Scanner scanner = new Scanner(System.in);
	private EmpDAO empDAO = EmpDAO.getInstance();
	
	public void run() {
		boolean role = LoginControl.selectLoginRole();
		while(true) {
			menuPrint(role);
			
			int menuNo = menuSelect();
			
			if(menuNo == 1 && role) {
				insertEmp();				
			}if(menuNo == 2 && role) {
				updateEmp();				
			}if(menuNo == 3 && role) {
				deleteEmp();				
			}if(menuNo == 4) {
				selectEmpInfo();				
			}if(menuNo == 5) {
				selectEmpAll();
			}if(menuNo == 9) {
				break;
			}
		}
		end();
	}
	
	private void end() {
		System.out.println("메인 메뉴로 돌아갑니다.");
	}
	
	private void menuPrint(boolean role) {
		String menu = "";
		if(role) {
			menu += "1.사원등록 ";
			menu += "2.사원수정 ";
			menu += "3.사원삭제 ";
		}
		menu += "4.사원조회 ";
		menu += "5.전체조회 ";
		menu += "9.뒤로가기 ";
		System.out.println("");
		System.out.println("===");
		System.out.println(menu);
		System.out.println("===");
	}
	
	private int menuSelect() {
		int menuNo = 0;
		try {
			System.out.print("선택>> ");
			menuNo = scanner.nextInt();
		}catch(NumberFormatException e) {
			System.out.println("숫자를 입력해주시기 바랍니다.");
		}
		return menuNo;
	}
	
	private void insertEmp() {
		Employee emp = inputAll();
		
		empDAO.insertEmpInfo(emp);
	}
	
	private void updateEmp() {
		Employee emp = inputInfo();
		
		empDAO.updateEmpInfo(emp);
	}
	
	private void deleteEmp() {
		int employeeId = inputEmpId();
		
		empDAO.deleteEmpInfo(employeeId);
	}
	
	private void selectEmpInfo() {
		int employeeId = inputEmpId();
		
		Employee emp = empDAO.selectEmpInfo(employeeId);
		System.out.println(emp);
	}

	private void selectEmpAll() {
		List<Employee> list = empDAO.selectEmpAll();
		for(Employee data : list) {
			System.out.println(data);
		}
	}
	
	private Employee inputAll() {
		Employee emp = new Employee();
		System.out.println("사번>>");
		emp.setEmployeeId(scanner.nextInt());
		System.out.println("이름>>");
		emp.setFirstName(scanner.next());
		System.out.println("성>>");
		emp.setLastName(scanner.next());
		System.out.println("이메일>>");
		emp.setEmail(scanner.next());
		System.out.println("전화번호>>");
		emp.setPhoneNumber(scanner.next());
		// 입력형식 : YYYY-MM-DD
		System.out.println("입사일>>");
		emp.setHireDate(Date.valueOf(scanner.next()));
		System.out.println("직급>>");
		emp.setJobId(scanner.next());
		System.out.println("연봉>>");
		emp.setSalary(scanner.nextInt());
		System.out.println("상여>>");
		emp.setCommissionPct(scanner.nextDouble());
		System.out.println("상사>>");
		emp.setManagerId(scanner.nextInt());
		System.out.println("부서>>");
		emp.setDepartmentId(scanner.nextInt());
		
		return emp;
	}
	
	private int inputEmpId() {
		System.out.println("사번>>");
		int employeeId = scanner.nextInt();
		return employeeId;
	}
	
	private Employee inputInfo() {
		Employee emp = new Employee();
		System.out.println("사번>>");
		emp.setEmployeeId(scanner.nextInt());
		System.out.println("연봉>>");
		emp.setSalary(scanner.nextInt());
		return emp;
	}
	
}
