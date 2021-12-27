package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookDelete {

	public static void main(String[] args) {

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
			
			//3. SQL문 준비 / 바인딩 / 실행
			// 3.1 문자열 만들기, 데이터는 ?로 변경
			String query = "";
			query += " delete  from book ";
			query += " where   book_id = ? ";
			System.out.println(query);
			
			// 3.2 문자열을 쿼리문으로 만들기
			pstmt = conn.prepareStatement(query);
			
			// 3.3 바인딩
			pstmt.setInt(1, 1);
			
			// 3.4 실행
			int count = pstmt.executeUpdate();
			
			//4.결과처리
			System.out.println(count + " 건이 삭제되었습니다.");
		
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

}