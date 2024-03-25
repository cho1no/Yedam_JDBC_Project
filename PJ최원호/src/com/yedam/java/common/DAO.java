package com.yedam.java.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAO {
	// Oracle(DB) 연결정보
	protected String jdbcDriver = "oracle.jdbc.driver.OracleDriver";
	protected String oracleUrl = "jdbc:oracle:thin:@localhost:1521:xe";
	protected String connectedId = "C##library";
	protected String connectedPwd = "1234";

	// 각 메소드에서 공통적으로 사용하는 전역 필드
	protected Connection conn = null;
	protected PreparedStatement pstmt = null;
	protected Statement stmt = null;
	protected ResultSet rs = null;

	// 생성자
	protected DAO() {
	}

	// 메소드
	// DB 연결
	protected void connect() {
		try {
			// JDBC Driver 로딩하기
			Class.forName(jdbcDriver);
			// DB 서버 접속하기
			conn = DriverManager.getConnection(oracleUrl, connectedId, connectedPwd);
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC Driver Loading Fail");
		} catch (SQLException e) {
			System.out.println("DB Connect Fail");
			System.out.println(e.getMessage());
		}
	}

	// DB 해제
	protected void disconnect() {
		try {
			// 자원 해제하기
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
