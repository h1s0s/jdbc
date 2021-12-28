package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookSelect {

	public static void main(String[] args) {

		
		List<BookVo> bookList = new ArrayList<BookVo>();
		
		// Book 데이터 가져오기
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
				query += " select  book_id,  ";
				query += "         title, ";
				query += "         pubs, ";
				query += "         pub_date, ";
				query += "         author_id ";
				query += " from    book ";
				System.out.println(query);
				
				// 3.2 문자열을 쿼리문으로 만들기
				pstmt = conn.prepareStatement(query);
				
				// 3.3 실행
				rs = pstmt.executeQuery();
					
				//4.결과처리 (select문에는 이부분이 어려움)
				//next()는 커서를 다음으로 옮김. 갈 수 있으면 true, 없으면 false가 나옴
				while(rs.next()) {
					int bId = rs.getInt("book_id");//컬럼명, 별명 사용시 별명
					String bTitle = rs.getString("title");
					String bPubs = rs.getString("pubs");
					String bPubDate = rs.getString("pub_date");
					int bAuthorId = rs.getInt("author_id");
					
					BookVo bvo = new BookVo(bId, bTitle, bPubs, bPubDate, bAuthorId);
					
					bookList.add(bvo);
					
					//System.out.println(bookId + ", " + bookTitle + ", " + bookPubs + ", " + bookPubDate + ", " + bookAuthorId);
				}
				
				//출력하기
				for(int i=0; i<bookList.size(); i++) {
					System.out.println(bookList.get(i).getBookId() + ", " + bookList.get(i).getBookTitle() + ", " +bookList.get(i).getBookPubs() + ", " +bookList.get(i).getBookPubDate() + ", " + bookList.get(i).getBookAuthorId());
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
