package com.yedam.java.member;

import java.sql.SQLException;

import com.yedam.java.common.DAO;

public class MemberDAO extends DAO {
	// 싱글톤
	private static MemberDAO memberDAO = null;

	private MemberDAO() {
	}

	public static MemberDAO getInstance() {
		if (memberDAO == null) {
			memberDAO = new MemberDAO();
		}
		return memberDAO;
	}

	// CRUD
	public Member selectOne(Member member) {
		Member loginInfo = null;
		try {
			connect();
			String sql = "SELECT * "
					   + "FROM members "
					   + "WHERE mem_id = '"+member.getMemId() + "'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				// 아이디 존재
				if (rs.getString("mem_pw").equals(member.getMemPw())) {
					// 비밀번호 일치
					// -> 로그인 성공
					loginInfo = new Member();
					loginInfo.setMemId(rs.getString("mem_id"));
					loginInfo.setMemPw(rs.getString("mem_pw"));
					loginInfo.setMemTel(rs.getString("mem_tel"));
					loginInfo.setMemLevel(rs.getInt("mem_level"));
				} else {
					System.out.println("비밀번호가 일치하지 않습니다.");
				}
			} else {
				System.out.println("아이디가 존재하지 않습니다.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return loginInfo;
	}
	
	public int newMember(Member member) {
		int result = 0;
		try {
			connect();
			String sql = "INSERT INTO members "
					   + "( mem_id, mem_pw, mem_tel ) "
					   + "VALUES "
					   + "( ?, ?, ? )";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, member.getMemId());
			pstmt.setString(2, member.getMemPw());
			pstmt.setString(3, member.getMemTel());
			
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result;
	}
}
