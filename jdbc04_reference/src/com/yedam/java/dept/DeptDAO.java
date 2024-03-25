package com.yedam.java.dept;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.java.common.DAO;

public class DeptDAO extends DAO {
	//싱글톤
	private static DeptDAO dao = null;
	private DeptDAO() {}
	public static DeptDAO getInstance() {
		if( dao == null) {
			dao = new DeptDAO();
		}
		return dao;
	}
	//메소드
	//1) 전체조회
	public List<Department> selectDeptAll(){
		List<Department> list = new ArrayList<>();
		try {
			// DB 접속
			connect();
			
			// SQL 작성
			String sql = "SELECT * "
						+"FROM departments "
						+"ORDER BY department_id";

			// 객체 생성
			stmt = conn.createStatement();
			
			// SQL 실행
			rs = stmt.executeQuery(sql);
			
			// 결과처리
			while(rs.next()) {
				// 한 행에 대한 처리
				Department dept = new Department();
				dept.setDepartmentId(rs.getInt("department_id"));
				dept.setDepartmentName(rs.getString("department_name"));
				dept.setManagerId(rs.getInt("manager_id"));
				dept.setLocationId(rs.getInt("location_id"));
				
				list.add(dept);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			// DB 해제
			disconnect();
		}
		return list;
	}
	//2) 단건조회
	public Department selectDeptInfo(int deptId) {
		Department dept = null;
		try {
			// DB 접속
			connect();
			
			// SQL 작성
			String sql = "SELECT * "
						+"FROM departments "
						+"WHERE department_id = ? ";
			
			// 객체 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, deptId);
			
			// SQL 실행
			rs = pstmt.executeQuery();
			
			// 결과처리
			if(rs.next()) {
				dept = new Department();
				dept.setDepartmentId(rs.getInt("department_id"));
				dept.setDepartmentName(rs.getString("department_name"));
				dept.setManagerId(rs.getInt("manager_id"));
				dept.setLocationId(rs.getInt("location_id"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			// DB 해제
			disconnect();
		}
		return dept;
	}
	//3) 등록
	public int insertDeptInfo(Department dept) {
		int result = 0;
		try {
			// DB 접속
			connect();
			
			// SQL 작성
			String sql = "INSERT INTO departments "
						+"VALUES (?, ?, ?, ?)";
			
			// 객체 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dept.getDepartmentId());
			pstmt.setString(2, dept.getDepartmentName());
			pstmt.setInt(3, dept.getManagerId());
			pstmt.setInt(4, dept.getLocationId());
			
			// SQL 실행
			result = pstmt.executeUpdate();
			
			// 결과처리 => return 구문
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			// DB 해제
			disconnect();
		}
		return result;
	}
	//4) 수정
	public int updateDeptInfo(Department dept) {
		int result = 0;
		try {
			// DB 접속
			connect();
			
			// SQL 작성
			String sql = "UPDATE departments "
						+"SET department_name = ? "
						+"WHERE department_id = ? ";
			
			// 객체 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(2, dept.getDepartmentId());
			pstmt.setString(1, dept.getDepartmentName());

			// SQL 실행
			result = pstmt.executeUpdate();
			
			// 결과처리 => return 구문
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			// DB 해제
			disconnect();
		}
		return result;
	}
	//5) 삭제
	public int deleteDeptInfo(int deptId) {
		int result = 0;
		try {
			// DB 접속
			connect();
			
			// SQL 작성
			String sql = "DELETE FROM departments "
					    +"WHERE department_id = " + deptId;
			
			// 객체 생성
			stmt = conn.createStatement();
			
			// SQL 실행
			result = stmt.executeUpdate(sql);
			
			// 결과처리 => return 구문 
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			// DB 해제
			disconnect();
		}
		return result;
	}
	
}
