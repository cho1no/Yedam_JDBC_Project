package com.yedam.app.emp;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class EmpApp {

	public static void main(String[] args) {
		/* DAO Test
		EmpDAO dao = EmpDAO.getInstance();
		List<Employee> datas = dao.selectAll();
		for(Employee emp : datas) {
			System.out.println(emp);
		}
		*/

		boolean run = true;
		
		Scanner scanner = new Scanner(System.in);
		EmpDAO empDao = EmpDAO.getInstance();
		Employee emp = null;
		
		while(run) {
			System.out.println("");
			System.out.println("====================================================");
			System.out.println("1.등록   2.수정  3.삭제  4.사원조회  5.사원전체조회  9.종료");
			System.out.println("====================================================");
			
			System.out.print("선택>> ");
			int menuNo = scanner.nextInt();
			
			if(menuNo == 1) {
				emp = new Employee();
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
				
				empDao.insertEmpInfo(emp);
			}if(menuNo == 2) {
				emp = new Employee();
				System.out.println("사번>>");
				emp.setEmployeeId(scanner.nextInt());
				System.out.println("연봉>>");
				emp.setSalary(scanner.nextInt());
				empDao.updateEmpInfo(emp);
			}if(menuNo == 3) {
				System.out.println("사번>>");
				int employeeId = scanner.nextInt(); 
				empDao.deleteEmpInfo(employeeId);
			}if(menuNo == 4) {
				System.out.println("사번>>");
				int employeeId = scanner.nextInt();
				emp = empDao.selectEmpInfo(employeeId);
				System.out.println(emp);
			}if(menuNo == 5) {
				List<Employee> list = empDao.selectEmpAll();
				for(Employee data : list) {
					System.out.println(data);
				}
			}if(menuNo == 9) {
				run = false;
			}
		}
		System.out.println("프로그램을 종료합니다.");
		scanner.close();
	}

}
