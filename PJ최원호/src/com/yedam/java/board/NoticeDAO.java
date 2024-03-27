package com.yedam.java.board;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.java.app.LoginControl;
import com.yedam.java.common.DAO;
public class NoticeDAO extends DAO{
	// 싱글톤
	private static NoticeDAO ntDAO = null;
	
	private NoticeDAO() {}
	
	public static NoticeDAO getInstance() {
		if (ntDAO == null)
			ntDAO = new NoticeDAO();
		return ntDAO;
	}
	
	// 메소드
	// 전체 가져오기
	public List<Notice> selectNoticeAll(){
		List<Notice> list = new ArrayList<>();
		try {
			// DB 연결
			connect();
			
			// 객체생성
			String sql = "SELECT * "
					   + "FROM board_notice "
					   + "ORDER BY notice_key";
			stmt = conn.createStatement();
			
			// SQL실행
			rs = stmt.executeQuery(sql);
			
			// 결과처리
			while(rs.next()) {
				Notice nt = setNotice();
//				String text = Management.setLength(nt.getNoticeTitle(), 12);
//				nt.setNoticeTitle(text);
				//nt.setNoticeWriter(Management.setLength(nt.getNoticeWriter(),5));
				list.add(nt);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			// DB종료
			disconnect();
		}
		return list;
	}
	// 개별 조회
	public Notice selectNotice(int noticeKey) {
		Notice nt = new Notice();
		try {
			// DB 연결
			connect();
			
			// 객체생성
			String sql = "SELECT * "
					   + "FROM board_notice "
					   + "WHERE notice_key = " + noticeKey;
			stmt = conn.createStatement();
			
			// SQL실행
			rs = stmt.executeQuery(sql);
			
			// 결과처리
			if(rs.next()) {
				nt = setNotice();
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			// DB종료
			disconnect();
		}
		return nt;
	}
	private Notice setNotice() throws SQLException {
		Notice nt = new Notice();
		nt.setNoticeKey(rs.getInt("notice_key"));
		nt.setNoticeTitle(rs.getString("notice_title"));
		nt.setNoticeDetail(rs.getString("notice_detail"));
		nt.setNoticeWriter(rs.getString("notice_writer"));
		nt.setNoticeWriteDay(rs.getDate("notice_write_day"));
		return nt;
	}
	
	// 등록
	public int insertNotice(Notice nt) {
		int result = 0;
		try {
			// DB 연결
			connect();
			// 객체생성
			String sql = "INSERT INTO board_notice "
					   + "(notice_key, notice_title, notice_detail, notice_writer) "
					   + "VALUES "
					   + "(board_notice_seq.NEXTVAL, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nt.getNoticeTitle());
			pstmt.setString(2, nt.getNoticeDetail());
			pstmt.setString(3, LoginControl.userId());
			
			// SQL실행
			result = pstmt.executeUpdate();
			
			// 결과처리 => return
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			// DB 종료
			disconnect();
		}
		return result;
	}
	// 수정 -> 작성권한이 있는 유저는 모두 관리자라 수정한 사람을 작성자로 바꿈
	public int updateNotice(Notice nt) {
		int result = 0;
		try { 
			// DB 연결
			connect();
			
			// 객체생성
			String sql = "UPDATE board_notice "
					   + "SET notice_title = ?, "
					   + "	  notice_detail = ?,"
					   + "	  notice_writer = ? "
					   + "WHERE notice_key = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nt.getNoticeTitle());
			pstmt.setString(2, nt.getNoticeDetail());
			pstmt.setString(3, LoginControl.userId());
			pstmt.setInt(4, nt.getNoticeKey());
			// SQL실행
			result = pstmt.executeUpdate();
			
			// 결과처리 => return
		} catch(SQLException e) {
			
		} finally {
			// DB 종료
			disconnect();
		}
		return result;
	}
	// 삭제
	public int deleteNotice(int noticeKey) {
		int result = 0;
		try {
			// DB 연결
			connect();
			// 객체생성
			String sql = "DELETE FROM board_notice "
					   + "WHERE notice_key =  " + noticeKey;
			stmt = conn.createStatement();
			// SQL실행
			result = stmt.executeUpdate(sql);
			// 결과처리 => return
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			// DB 종료
			disconnect();
		}
		return result;
	}
}
