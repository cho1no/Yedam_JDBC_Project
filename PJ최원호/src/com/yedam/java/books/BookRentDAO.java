package com.yedam.java.books;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.java.app.LoginControl;
import com.yedam.java.common.DAO;

public class BookRentDAO extends DAO{
	// 싱글톤
	private static BookRentDAO bookRentDAO = null;
	
	private BookRentDAO() {}

	public static BookRentDAO getInstance() {
		if (bookRentDAO == null)
			bookRentDAO = new BookRentDAO();
		return bookRentDAO;
	}

	// 메소드
	// 도서 대여기록 조회
	public List<BookRent> selectRentBook() {
		List<BookRent> list = new ArrayList<>();
		try {
			// DB 연결
			connect();
			
			// 객체생성
			String sql = "SELECT * "
					   + "FROM book_rental_list br "
					   + "	JOIN books b "
					   + "	ON br.b_no = b.b_no ";
			sql +=  "ORDER BY br.rent_key";
			stmt = conn.createStatement();
			
			// SQL실행
			rs = stmt.executeQuery(sql);
			
			// 결과처리
			while(rs.next()) {
				BookRent br = new BookRent();
				br.setRentKey(rs.getInt("rent_key"));
				br.setbNo(rs.getInt("b_no"));
				br.setRenter(rs.getString("renter"));
				br.setStart(rs.getDate("start_rent"));
				br.setEnd(rs.getDate("end_rent"));
				br.setIsReturn(rs.getInt("isreturn"));
				br.setbTitle(rs.getString("b_title"));
				br.setbWriter(rs.getString("b_writer"));
				list.add(br);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			// DB종료
			disconnect();
		}
		return list;
	}
	// 사용자 도서 대여기록 조회
	public List<BookRent> selectRentBook(boolean isAll) {
		List<BookRent> list = new ArrayList<>();
		try {
			// DB 연결
			connect();
			
			// 객체생성
			String sql = "SELECT * "
					   + "FROM book_rental_list br "
					   + "	JOIN books b "
					   + "	ON br.b_no = b.b_no "
					   + "WHERE br.renter = '" + LoginControl.userId() + "' ";
			if (!isAll) {
				sql += "AND br.isreturn <> 1 ";
			}
			sql +=  "ORDER BY br.rent_key";
			stmt = conn.createStatement();
			
			// SQL실행
			rs = stmt.executeQuery(sql);
			
			// 결과처리
			while(rs.next()) {
				BookRent br = new BookRent();
				br.setRentKey(rs.getInt("rent_key"));
				br.setbNo(rs.getInt("b_no"));
				br.setRenter(rs.getString("renter"));
				br.setStart(rs.getDate("start_rent"));
				br.setEnd(rs.getDate("end_rent"));
				br.setIsReturn(rs.getInt("isreturn"));
				br.setbTitle(rs.getString("b_title"));
				br.setbWriter(rs.getString("b_writer"));
				list.add(br);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			// DB종료
			disconnect();
		}
		return list;
	}
	// 반납
	public int updateRented(int rentKey) {
		int result = 0;
		try {
			// DB연결
			connect();
			// 재고 확인
			// 객체생성
			String sql = "UPDATE book_rental_list "
					   + "SET isReturn = 1 "
					   + "WHERE rent_key = ? AND isreturn <> 1 AND renter = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rentKey);
			pstmt.setString(2, LoginControl.userId());
			// SQL실행
			result = pstmt.executeUpdate();
			if (result > 0) {
				sql = "UPDATE books "
					+ "SET b_inventory = b_inventory + 1 "
					+ "WHERE b_no = (SELECT b_no "
					+ "				 FROM book_rental_list "
					+ "				 WHERE rent_key = " + rentKey + ")";
				stmt = conn.createStatement();
				stmt.executeQuery(sql);
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
	// 대여
	public int newRent(int bNo) {
		int result = 0;
		try {
			// DB연결
			connect();
			// 객체생성 : 재고 증가
			String sql = "UPDATE books "
					   + "SET b_inventory = b_inventory - 1 "
					   + "WHERE b_no = " + bNo;
			stmt = conn.createStatement();
			// 실행
			stmt.executeQuery(sql);
			// 객체생성
			sql = "INSERT INTO book_rental_list "
				+ "("
				+ "rent_key"
				+ ", b_no"
				+ ", renter"
				+ ")"
				+ "VALUES"
				+ "("
				+ "rental_list_seq.NEXTVAL"
				+ ", ?"
				+ ", ?"
				+ ")";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bNo);
			pstmt.setString(2, LoginControl.userId());
			
			// SQL실행
			result = pstmt.executeUpdate();		
			// 결과 return
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// DB해제
			disconnect();
		}
		return result;
	}
	// 연체정보 업데이트
	public void updateOverRent(int rentKey) {
		try {
			// DB연결
			connect();
			// 객체생성
			String sql = "UPDATE book_rental_list "
					   + "SET isReturn = 2 "
					   + "WHERE rent_key = ? AND isReturn = 0";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rentKey);
			// SQL실행
			pstmt.executeUpdate();
			// 결과 return
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// DB해제
			disconnect();
		}
	}
}
