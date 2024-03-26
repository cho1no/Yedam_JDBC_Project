package com.yedam.java.books;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.java.common.DAO;

public class BookDAO extends DAO{
	// 싱글톤
	private static BookDAO bookDAO = null;
	
	private BookDAO() {}
	
	public static BookDAO getInstance() {
		if (bookDAO == null)
			bookDAO = new BookDAO();
		return bookDAO;
	}
	
	// 메소드
	// 전체 조회
	public List<Book> selectBookAll(){
		List<Book> list = new ArrayList<>();
		try {
			// DB 연결
			connect();
			
			// 객체생성
			String sql = "SELECT * "
					   + "FROM books "
					   + "ORDER BY b_no";
			stmt = conn.createStatement();
			
			// SQL실행
			rs = stmt.executeQuery(sql);
			
			// 결과처리
			while(rs.next()) {
				list.add(setBook());
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			// DB종료
			disconnect();
		}
		return list;
	}
	
	// 단건 조회
	public Book selectBook(int bNo) {
		Book book = null;
		try {
			// DB 연결
			connect();
			
			// 객체생성
			String sql = "SELECT * "
					   + "FROM books "
					   + "WHERE b_no = " + bNo;
			stmt = conn.createStatement();
			
			// SQL실행
			rs = stmt.executeQuery(sql);
			
			// 결과처리
			if(rs.next()) {
				book = setBook();
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			// DB종료
			disconnect();
		}
		return book;
	}
	
	// 검색
	public List<Book> searchBook(String search){
		List<Book> list = new ArrayList<>();
		try {
			// DB 연결
			connect();
			
			// 객체생성
			String sql = "SELECT * "
					   + "FROM books "
					   + "WHERE b_no LIKE ? OR "
					   + "		b_title LIKE ? OR"
					   + "		b_writer LIKE ? OR"
					   + "		b_info LIKE ? "
					   + "ORDER BY b_no";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + search + "%");
			pstmt.setString(2, "%" + search + "%");
			pstmt.setString(3, "%" + search + "%");
			pstmt.setString(4, "%" + search + "%");
			
			// SQL실행
			rs = pstmt.executeQuery();
			
			// 결과처리
			while(rs.next()) {
				list.add(setBook());
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			// DB종료
			disconnect();
		}
		return list;
	}
	// 등록시 중복된 책
//	public boolean isExist(Book book) {
//		boolean exist = false;
//		try {
//			// DB 연결
//			connect();
//			
//			// 객체생성
//			String sql = "SELECT * "
//					   + "FROM books "
//					   + "WHERE b_title = ? AND"
//					   + "		b_writer = ? "
//					   + "ORDER BY b_no";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, book.getbTitle());
//			pstmt.setString(2, book.getbWriter());
//			
//			// SQL실행
//			rs = pstmt.executeQuery();
//			
//			// 결과처리
//			if (rs.next()) {
//				exist = true;
//			}
//			
//		} catch(SQLException e) {
//			e.printStackTrace();
//		} finally {
//			// DB종료
//			disconnect();
//		}
//		
//		return exist;
//	}
	
	// 책 저장
	private Book setBook() throws SQLException {
		Book book = new Book();
		
		book.setbNo(rs.getInt("b_no"));
		book.setbTitle(rs.getString("b_title"));
		book.setbWriter(rs.getString("b_writer"));
		book.setbInfo(rs.getString("b_info"));
		book.setbInventroy(rs.getInt("b_inventory"));
		book.setbCreateDate(rs.getDate("b_created_date"));
		
		return book;
	}
	
	// 등록
	public int insertBook(Book book) {
		int result = 0;
		try {
			// DB 연결
			connect();
			
			// 객체생성
			String sql = "INSERT INTO books "
					   + "(b_no, b_title, b_writer, b_info, b_inventory)"
					   + "VALUES"
					   + "(book_id_seq.NEXTVAL, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, book.getbTitle());
			pstmt.setString(2, book.getbWriter());
			pstmt.setString(3, book.getbInfo());
			pstmt.setInt(4, book.getbInventroy());
			
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
	
	// 수정
	public int updateBook(Book book) {
		int result = 0;
		try { 
			// DB 연결
			connect();
			
			// 객체생성
			String sql = "UPDATE books "
					   + "SET b_title = ?, "
					   + "	  b_writer = ?,"
					   + "	  b_info = ? "
					   + "WHERE b_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, book.getbTitle());
			pstmt.setString(2, book.getbWriter());
			pstmt.setString(3, book.getbInfo());
			pstmt.setInt(4, book.getbNo());
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
	public int deleteBook(int bNo) {
		int result = 0;
		try {
			// DB 연결
			connect();
			// 객체생성
			String sql = "DELETE FROM books "
					   + "WHERE b_no = " + bNo;
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
