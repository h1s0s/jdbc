package com.javaex.ex07;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

	// 필드
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	// 생성자

	// 메소드 g/s

	// 메소드 일반
	private void getConnection() {
		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url, id, pw);
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

	}

	private void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	public void bookInsert(BookVo bookVo) {
		getConnection();
		try {
			String query = "";
			query += " insert into book ";
			query += " values (seq_book_id.nextval, ?, ?, ?, ?) ";

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, bookVo.getTitle());
			pstmt.setString(2, bookVo.getPubs());
			pstmt.setString(3, bookVo.getPubDate());
			pstmt.setInt(4, bookVo.getAuthorId());

			int count = pstmt.executeUpdate();

			System.out.println(count + " 건이 생성되었습니다.(북)");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close();
	}

	public void BookUpdate(BookVo bookVo) {
		getConnection();
		try {
			String query = "";
			query += " update  book ";
			query += " set     title = ?, ";
			query += " 		   pubs = ?, ";
			query += " 		   pub_date = ? ";
			query += " where   book_id = ? ";
			
			// 3.2 문자열을 쿼리문으로 만들기
			pstmt = conn.prepareStatement(query);

			// 3.3 바인딩
			pstmt.setString(1, bookVo.getTitle());
			pstmt.setString(2, bookVo.getPubs());
			pstmt.setString(3, bookVo.getPubDate());
			pstmt.setInt(4, bookVo.getBookId());

			// 3.4 실행
			int count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + " 건이 저장되었습니다.(북)");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close();
	}

	public void BookDelete(int bookId) {
		getConnection();
		try {
			String query = "";
			query += " delete  from book ";
			query += " where   book_id = ? ";
			
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, bookId);

			int count = pstmt.executeUpdate();

			System.out.println(count + " 건이 삭제되었습니다.(책)");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close();
	}

	public List<BookVo> BookSelect() {
		List<BookVo> bookList = new ArrayList<BookVo>();

		getConnection();
		try {
			String query = "";
			query += " select  book_id,  ";
			query += "         title, ";
			query += "         pubs, ";
			query += "         to_char(pub_date, 'YYYY-MM-DD'), ";
			query += "         author_id ";
			query += " from    book ";

			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int bId = rs.getInt(1);
				String bTitle = rs.getString(2);
				String bPubs = rs.getString(3);
				String bPubDate = rs.getString(4);
				int aid = rs.getInt(5);

				BookVo bvo = new BookVo(bId, bTitle, bPubs, bPubDate, aid);
				bookList.add(bvo);

			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close();
		return bookList;
	}
}
