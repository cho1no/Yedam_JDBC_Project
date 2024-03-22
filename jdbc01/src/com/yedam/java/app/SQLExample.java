package com.yedam.java.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLExample {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// 1) JDBC Driver 로딩하기
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2) DB 서버 접속하기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String id  = "hr";
			String pwd = "hr";
			
			conn = DriverManager.getConnection(url, id, pwd);
			
			/************************ INSERT ************************/
			// 3) Statement or PreparedStatement 객체 생성하기
			String insert = "INSERT INTO employees "
						  + "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(insert);
			pstmt.setInt(1, 1000);
			pstmt.setString(2, "Kil-Dong");
			pstmt.setString(3, "Hong");
			pstmt.setString(4, "kdHong@google.com");
			pstmt.setString(5, "82.10.1234.1234");
			pstmt.setString(6, "21/11/05");
			pstmt.setString(7, "SA_REP");
			pstmt.setInt(8, 6000);
			pstmt.setDouble(9, 0.15);
			pstmt.setInt(10, 149);
			pstmt.setInt(11, 80);
			// => 실제 쿼리문이 완성됨
			
			// 4) SQL 실행하기 - 위의 select문을 실행함
			int result = pstmt.executeUpdate();
			
			// 5) 결과값 처리하기
			System.out.println("Insert 결과 : " + result);
			
			/************************ UPDATE ************************/
			// 3) Statement or PreparedStatement 객체 생성하기
			String update = "UPDATE employees "
						  + "SET last_name = ? "
						  + "WHERE employee_id = ?";
			pstmt = conn.prepareStatement(update);
			pstmt.setString(1, "Kang");
			pstmt.setInt(2, 1000);
			
			// 4) SQL 실행하기 - 위의 select문을 실행함
			result = pstmt.executeUpdate();
			
			// 5) 결과값 처리하기
			System.out.println("Update 결과 : " + result);
			
			/************************ SELECT ************************/
			// 3) Statement or PreparedStatement 객체 생성하기
			String select = "SELECT * "
					 	  + "FROM employees "
					 	  + "ORDER BY employee_id DESC";
			
			stmt = conn.createStatement();
			
			// 4) SQL 실행하기 - 위의 select문을 실행함
			rs = stmt.executeQuery(select);
			
			// 5) 결과값 처리하기
			while(rs.next()) {
				String info = "사원번호 : " + rs.getString("employee_id")
						    + ", 이름 : "  + rs.getString("first_name")
						    + ", 성 : "	   + rs.getString("last_name");
				System.out.println(info);
			}
			
			/************************ DELETE ************************/
			// 3) Statement or PreparedStatement 객체 생성하기
			String delete = "DELETE FROM employees "
						  + "WHERE employee_id = " + 1000;
			stmt = conn.createStatement();
			
			// 4) SQL 실행하기 - 위의 select문을 실행함
			result = stmt.executeUpdate(delete);
			
			// 5) 결과값 처리하기
			System.out.println("Delete 결과 : " + result);
			
		} catch(ClassNotFoundException e) {
			// JDBC Driver Loading Fail
			System.out.println("JDBC Driver Loading Fail");
		} catch(SQLException e){
			e.printStackTrace();
		} finally {
			try {
				// 6) 자원 해제하기
				if(rs != null) 	  rs.close();
				if(pstmt != null) pstmt.close();
				if(stmt != null)  stmt.close();
				if(conn != null)  conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
