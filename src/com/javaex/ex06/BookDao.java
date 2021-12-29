package com.javaex.ex06;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

	// 필드

	// 생성자
	public BookDao() {

	}

	// 메소드 g/s

	// 메소드 일반
	public void bookInsert(BookVo bookVo) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			// System.out.println("접속성공");

			// 3. SQL문 준비 / 바인딩 / 실행
			// 3.1 문자열 만들기, 데이터는 ?로 변경
			String query = "";
			query += " insert into book ";
			query += " values (seq_book_id.nextval, ?, ?, ?, ?) ";
			// System.out.println(query);

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
			System.out.println(count + " 건이 생성되었습니다.(북)");

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. 자원정리
			try {
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
	}

	public void BookUpdate(BookVo bookVo) {

		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			System.out.println("접속성공");

			// 3. SQL문 준비 / 바인딩 / 실행
			// 3.1 문자열 만들기, 데이터는 ?로 변경
			String query = "";
			query += " update  book ";
			query += " set     title = ? ";
			query += " set book_pubs = ?, ";
			query += " set book_pub_date = ? ";
			query += " where   book_id = ? ";
			System.out.println(query);

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
			System.out.println(count + " 건이 저장되었습니다.");

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. 자원정리
			try {
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
	}

	public void BookDelete(int bookId) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			System.out.println("접속성공");

			// 3. SQL문 준비 / 바인딩 / 실행
			// 3.1 문자열 만들기, 데이터는 ?로 변경
			String query = "";
			query += " delete  from book ";
			query += " where   book_id = ? ";

			// 3.2 문자열을 쿼리문으로 만들기
			pstmt = conn.prepareStatement(query);

			// 3.3 바인딩
			pstmt.setInt(1, bookId);

			// 3.4 실행
			int count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + " 건이 삭제되었습니다.(책)");

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. 자원정리
			try {
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
	}

	public List<BookVo> BookSelect() {
		List<BookVo> bookList = new ArrayList<BookVo>();
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. Connection 얻어오기
			// 오라클에 정보 요청하기
			String url = "jdbc:oracle:thin:@localhost:1521:xe"; // localhost = ip주소:포트:시드
			conn = DriverManager.getConnection(url, "webdb", "webdb");// 연결해라, 전화선이 생김, url, "id", "pw"
			System.out.println("접속성공");

			// 3. SQL문 준비 / 바인딩 / 실행
			// 3.1 문자열 만들기
			String query = "";
			query += " select  book_id,  ";
			query += "         title, ";
			query += "         pubs, ";
			query += "         pub_date ";
			query += " from    book ";
			System.out.println(query);

			// 3.2 문자열을 쿼리문으로 만들기
			pstmt = conn.prepareStatement(query);

			// 3.3 실행
			rs = pstmt.executeQuery();

			// 4.결과처리 (select문에는 이부분이 어려움)
			// next()는 커서를 다음으로 옮김. 갈 수 있으면 true, 없으면 false가 나옴
			while (rs.next()) {
				int bId = rs.getInt(1);// 컬럼명, 별명 사용시 별명
				String bTitle = rs.getString(2);
				String bPubs = rs.getString(3);
				String bPubDate = rs.getString(4);

				BookVo bvo = new BookVo(bId, bTitle, bPubs, bPubDate);
				bookList.add(bvo);

			}

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
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
		return bookList;
	}
}
