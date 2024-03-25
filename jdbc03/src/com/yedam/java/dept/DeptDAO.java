package com.yedam.java.dept;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.java.common.DAO;

public class DeptDAO extends DAO{
	// 싱글톤
	private static DeptDAO deptDAO = null;
	
	private DeptDAO() {}
	
	public static DeptDAO getInstance() {
		if (deptDAO == null)
			deptDAO = new DeptDAO();
		return deptDAO;
	}
	
	// 메소드
	// 전체조회
	public List<Department> selectDeptAll(){
		List<Department> list = new ArrayList<>();
		try {
			connect();
			
			String select = "SELECT * FROM departments ORDER BY department_id ASC";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(select);
			
			while(rs.next()) {
				Department dept = new Department();
				
				dept.setDepartmentId(rs.getInt("department_id"));
				dept.setDepartmentName(rs.getString("department_name"));
				dept.setManagerId(rs.getInt("manager_id"));
				dept.setLocationId(rs.getInt("location_id"));
				
				list.add(dept);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
	
	// 단건 조회
	public Department selectDeptInfo(int departmentId) {
		Department dept = null;
		try {
			connect();
			
			String select = "SELECT * FROM departments WHERE department_id = " + departmentId;
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(select);
			
			if (rs.next()) {
				dept = new Department();
				
				dept.setDepartmentId(rs.getInt("department_id"));
				dept.setDepartmentName(rs.getString("department_name"));
				dept.setManagerId(rs.getInt("manager_id"));
				dept.setLocationId(rs.getInt("location_id"));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return dept;
	}
	
	// 등록
	public int insertDeptInfo(Department dept) {
		int result = 0;
		try {
			connect();
			
			String insert = "INSERT INTO departments"
						  + " VALUES (?,?,?,?)";
			
			pstmt = conn.prepareStatement(insert);
			
			pstmt.setInt(1, dept.getDepartmentId());
			pstmt.setString(2, dept.getDepartmentName());
			pstmt.setInt(3, dept.getManagerId());
			pstmt.setInt(4, dept.getLocationId());
			
			result = pstmt.executeUpdate();

			// 결과 처리 => return 구문
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result;
	}
	
	// 수정
	public int updateDeptInfo(Department dept) {
		int result = 0;
		try {
			connect();
			
			String update = "UPDATE departments "
						  + "SET department_name = ? "
					  	  + "WHERE department_id = ?";
			pstmt = conn.prepareStatement(update);
			pstmt.setString(1, dept.getDepartmentName());
			pstmt.setInt(2, dept.getDepartmentId());
			result = pstmt.executeUpdate();
			
			//System.out.println("Update 결과 : " + result);
			
		} catch(SQLException e){
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result;
	}
	
	// 삭제
	public int deleteDeptInfo(int departmentId) {
		int result = 0;
		try {
			connect();
			
			String delete = "DELETE FROM departments WHERE department_id = " + departmentId;
			stmt = conn.createStatement();
			
			result = stmt.executeUpdate(delete);
			
			//System.out.println("Delete 결과 : " + result);
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result;
	}
}
