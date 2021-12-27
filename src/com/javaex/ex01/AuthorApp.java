package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorApp {

	public static void main(String[] args) {
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. Connection 얻어오기
			//오라클에 정보 요청하기
			String url = "jdbc:oracle:thin:@localhost:1521:xe"; // localhost = ip주소:포트:시드
			conn = DriverManager.getConnection(url, "webdb", "webdb");//연결해라, 전화선이 생김, url, "id", "pw"
			System.out.println("접속성공");
			
			// 3. SQL문 준비 / 바인딩 / 실행 ******이부분이 중요함
			//ㅇ문자열 만들기
			//쿼리문을 자바에 코드로 넣는것
			String query = "";
			//query = query + "문자열"
			query += "insert into author";
			query += "values (seq_author_id.nextval, ?, ?)";// sql => values (seq_author_id.nextval, '이문열', '경북 영양');
			//1. sql문의 세미콜론(마침표)는 뺀다.
			//2. +=는 기존 값에 붙여라.
			//3. 데이터에 해당하는 부분은(값이 들어갈 자리는, ?로 표시)
			
			//int count = pstmt.executeUpdate(); //insert, update, delete
			//rs = pstmt.executeQuery(); //select
			//문자열을 쿼리문으로 만들기
			pstmt = conn.prepareCall(query);
			
			//바인딩 (?에 데이터를 넣어주는 작업)
			//문자열일때는 setString, 숫자일때는 setInt
			pstmt.setString(1, "이문열");//첫번째 물음표에는 이문열
			pstmt.setString(2, "경북 영양");//두번째 물음표에는 경북 영양
			
			//실행
			int count = pstmt.executeUpdate(); //쿼리문 실행, 성공하면 값이 1, 실패하면 값은 0
			
			// 4.결과처리
			System.out.println(count + " 건이 저장되었습니다.");
			
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
		