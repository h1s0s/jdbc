package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookSelectAll {

	public static void main(String[] args) {
		// 책 데이터 출력하기 (조인)
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			//1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2. Connection 얻어오기
			//오라클에 정보 요청하기
			String url = "jdbc:oracle:thin:@localhost:1521:xe"; // localhost = ip주소:포트:시드
			conn = DriverManager.getConnection(url, "webdb", "webdb");//연결해라, 전화선이 생김, url, "id", "pw"
			System.out.println("접속성공");
			
			//3. SQL문 준비 / 바인딩 / 실행
			// 3.1 문자열 만들기
			String query = "";
			query += " select  bo.book_id id, bo.title title, bo.pubs pubs, to_char(bo.pub_date, 'YYYY-MM-DD'), au.author_id authorid, au.author_name authorname, author_desc authordesc ";
			query += " from    book bo, author au ";
			query += " where   au.author_id = bo.author_id ";
			System.out.println(query);
			
			// 3.2 문자열을 쿼리문으로 만들기
			pstmt = conn.prepareStatement(query);
			
			// 3.3 실행
			rs = pstmt.executeQuery();
				
			//4.결과처리 (select문에는 이부분이 어려움)
			//next()는 커서를 다음으로 옮김. 갈 수 있으면 true, 없으면 false가 나옴
			while(rs.next()) {
				int bookId = rs.getInt(1);//컬럼명, 별명 사용시 별명
				String bookTitle = rs.getString(2);
				String bookPubs = rs.getString(3);
				String bookPubDate = rs.getString(4);
				int bookAuthorId = rs.getInt(5);
				String bookAuthorName = rs.getString(6);
				String bookAuthorDesc = rs.getString(7);
				
				System.out.println(bookId + ", " + bookTitle + ", " + bookPubs + ", " + bookPubDate + ", " + bookAuthorId + ", " + bookAuthorName + ", " + bookAuthorDesc);
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
	}				
}
