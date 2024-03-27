package com.yedam.java.board;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.java.app.LoginControl;
import com.yedam.java.common.DAO;

public class ReviewDAO extends DAO{
	// 싱글톤
	private static ReviewDAO reDAO = null;
	private ReviewDAO() {}
	public static ReviewDAO getInstance() {
		if (reDAO == null)
			reDAO = new ReviewDAO();
		return reDAO;
	}
	
	// 메소드
	// 목록조회 -> 책별로 그룹 -> 평균평점(내림)
	public List<Review> selectReviewAll(){
		List<Review> list = new ArrayList<>();
		try {
			// DB 연결
			connect();
			
			// 객체생성
			String sql = "SELECT b.b_no "
					   + "        , b.b_title "
					   + "        , b.b_writer "
					   + "        , TRUNC(AVG(r.review_rate)) AS AVG_RATE "
					   + "FROM board_review r "
					   + "    JOIN books b "
					   + "    ON r.b_no = b.b_no "
					   + "GROUP BY b.b_no, b.b_title, b.b_writer "
					   + "ORDER BY b.b_no";
			stmt = conn.createStatement();
			
			// SQL실행
			rs = stmt.executeQuery(sql);
			
			// 결과처리
			while(rs.next()) {
				Review re = new Review();
				re.setbNo(rs.getInt("b_no"));
				re.setbTitle(rs.getString("b_title"));
				re.setbWriter(rs.getString("b_writer"));
				re.setReRate(rs.getInt("avg_rate"));
				list.add(re);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			// DB종료
			disconnect();
		}
		return list;
	}
	// 항목별 자세히보기
	public List<Review> selectReviewGroup(int bNo){
		List<Review> list = new ArrayList<>();
		try {
			// DB 연결
			connect();
			
			// 객체생성
			String sql = "SELECT * "
					   + "FROM board_review r "
					   + "    JOIN books b "
					   + "    ON r.b_no = b.b_no "
					   + "WHERE r.b_no = " + bNo + " "
					   + "ORDER BY r.review_write_day DESC";
			stmt = conn.createStatement();
			
			// SQL실행
			rs = stmt.executeQuery(sql);
			
			// 결과처리
			while(rs.next()) {
				Review re = new Review();
				re.setReKey(rs.getInt("review_key"));
				re.setbTitle(rs.getString("b_title"));
				re.setReDetail(rs.getString("review_detail"));
				re.setReRate(rs.getInt("review_rate"));
				re.setReWriter(rs.getString("review_writer"));
				re.setReWriteDay(rs.getDate("review_write_day"));
				list.add(re);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			// DB종료
			disconnect();
		}
		return list;
	}
	// 리뷰 등록
	public int insertReview(Review re) {
		int result = 0;
		try {
			// DB연결
			connect();
			// 객체생성
			String sql = "SELECT * "
					   + "FROM book_rental_list "
					   + "WHERE b_no = " + re.getbNo()
					   + " 		AND isreturn > 0 ";
			stmt = conn.createStatement();
			// 실행
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				// 객체생성
				sql = "INSERT INTO board_review "
					+ "("
					+ "review_key"
					+ ", b_no"
					+ ", review_detail"
					+ ", review_rate"
					+ ", review_writer"
					+ ")"
					+ "VALUES"
					+ "("
					+ "board_review_seq.NEXTVAL"
					+ ", ?"
					+ ", ?"
					+ ", ?"
					+ ", ?"
					+ ")";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, re.getbNo());
				pstmt.setString(2, re.getReDetail());
				pstmt.setInt(3, re.getReRate());
				pstmt.setString(4, LoginControl.userId());
				
				// SQL실행
				result = pstmt.executeUpdate();
			}
			// 결과 return
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// DB해제
			disconnect();
		}
		return result;
	}
	// 수정 -> 본인이 작성한 것만 수정가능 -> 검색조건 책번호
	public int updateReview(Review re) {
		int result = 0;
		try { 
			// DB 연결
			connect();

			// 객체생성
			String sql = "UPDATE board_review "
				+ "SET review_detail = ?, "
			    + "	   review_rate = ? "
			    + "WHERE b_no = ? AND review_writer = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, re.getReDetail());
			pstmt.setInt(2, re.getReRate());
			pstmt.setInt(3, re.getbNo());
			pstmt.setString(4, LoginControl.userId());
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
	// 삭제 -> 본인(책번호 선택) 혹은 관리자(리뷰번호선택)
	public int deleteReview(Review re) {
		int result = 0;
		try {
			// DB 연결
			connect();
			// 객체생성
			String sql = "";
			if (LoginControl.userLevel() == 9) {
				sql= "DELETE FROM board_review "
				   + "WHERE review_key = " + re.getReKey();
			} else {
				sql= "DELETE FROM board_review "
				   + "WHERE (b_no =  " + re.getbNo()
				   + " AND review_writer = '"+ LoginControl.userId() + "')";
			}
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
