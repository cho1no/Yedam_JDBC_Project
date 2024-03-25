package com.yedam.java.emp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.java.common.DAO;

public class EmpDAO extends DAO {
	// 싱글톤
	private static EmpDAO empDAO = null;
	
	private EmpDAO() {}
	
	public static EmpDAO getInstance() {
		if (empDAO == null)
			empDAO = new EmpDAO();
		return empDAO;
	}
	
	// 메소드
	// 전체조회
	public List<Employee> selectEmpAll() {
		List<Employee> list = new ArrayList<>();
		try {
			// 1. DB 연결
			connect();

			// 2. 객체 생성
			String select = "SELECT * " + "FROM employees " + "ORDER BY employee_id ASC";

			stmt = conn.createStatement();

			// 3. SQL 실행
			rs = stmt.executeQuery(select);

			// 4. 결과 처리
			while (rs.next()) {
				Employee emp = new Employee();

				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setPhoneNumber(rs.getString("phone_number"));
				emp.setHireDate(rs.getDate("hire_date"));
				emp.setJobId(rs.getString("job_id"));
				emp.setSalary(rs.getDouble("salary"));
				emp.setCommissionPct(rs.getDouble("commission_pct"));
				emp.setManagerId(rs.getInt("manager_id"));
				emp.setDepartmentId(rs.getInt("department_id"));

				list.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 5. 자원해제
			disconnect();
		}
		return list;
	}

	// 단건조회
	public Employee selectEmpInfo(int employeeId) {
		Employee emp = null;
		try {
			// 1. DB 연결
			connect();

			// 2. 객체 생성
			String select = "SELECT * " + "FROM employees " + "WHERE employee_id = ?";
			pstmt = conn.prepareStatement(select);
			pstmt.setInt(1, employeeId);

			// 3. SQL 실행
			rs = pstmt.executeQuery();

			// 4. 결과 처리
			if (rs.next()) {
				emp = new Employee();

				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setPhoneNumber(rs.getString("phone_number"));
				emp.setHireDate(rs.getDate("hire_date"));
				emp.setJobId(rs.getString("job_id"));
				emp.setSalary(rs.getDouble("salary"));
				emp.setCommissionPct(rs.getDouble("commission_pct"));
				emp.setManagerId(rs.getInt("manager_id"));
				emp.setDepartmentId(rs.getInt("department_id"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 5. 자원해제
			disconnect();
		}
		return emp;
	}

	// 등록
	public void insertEmpInfo(Employee emp) {
		try {
			// 1. DB 연결
			connect();

			// 2. 객체 생성
			String insert = "INSERT INTO employees " + "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(insert);
			pstmt.setInt(1, emp.getEmployeeId());
			pstmt.setString(2, emp.getFirstName());
			pstmt.setString(3, emp.getLastName());
			pstmt.setString(4, emp.getEmail());
			pstmt.setString(5, emp.getPhoneNumber());
			pstmt.setDate(6, emp.getHireDate()); // setDate의 date는 java.sql.date
			pstmt.setString(7, emp.getJobId());
			pstmt.setDouble(8, emp.getSalary());
			pstmt.setDouble(9, emp.getCommissionPct());
			pstmt.setInt(10, emp.getManagerId());
			pstmt.setInt(11, emp.getDepartmentId());

			// 3. SQL 실행
			int result = pstmt.executeUpdate();

			// 4. 결과 처리
			System.out.println("Insert 결과 : " + result);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 5. 자원해제
			disconnect();
		}
	}

	// 수정
	public void updateEmpInfo(Employee emp) {
		try {
			// 1. DB 연결
			connect();

			// 2. 객체 생성
			String update = "UPDATE employees " + "SET salary = ? " + "WHERE employee_id = ?";
			pstmt = conn.prepareStatement(update);
			pstmt.setDouble(1, emp.getSalary());
			pstmt.setInt(2, emp.getEmployeeId());

			// 3. SQL 실행
			int result = pstmt.executeUpdate();

			// 4. 결과 처리
			System.out.println("Update 결과 : " + result);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 5. 자원해제
			disconnect();
		}
	}

	// 삭제
	public void deleteEmpInfo(int employeeId) {
		try {
			// 1. DB 연결
			connect();

			// 2. 객체 생성
			String delete = "DELETE FROM employees " + "WHERE employee_id = " + employeeId;
			stmt = conn.createStatement();
			// 3. SQL 실행
			int result = stmt.executeUpdate(delete);

			// 4. 결과 처리
			System.out.println("Delete 결과 : " + result);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 5. 자원해제
			disconnect();
		}
	}
}
