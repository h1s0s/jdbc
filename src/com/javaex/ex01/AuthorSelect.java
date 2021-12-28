package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorSelect {

	public static void main(String[] args) {

		List<AuthorVo> authorList = new ArrayList<AuthorVo>();
		
		// Author 데이터 가져오기, 사용 빈도가 아주 높음
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
				query += " select author_id id, ";
				query += "        author_name, ";
				query += "        author_desc ";
				query += " from   author ";
				System.out.println(query);
				
				// 3.2 문자열을 쿼리문으로 만들기
				pstmt = conn.prepareStatement(query);
				
				// 3.3 실행
				rs = pstmt.executeQuery();
					
				//4.결과처리 (select문에는 이부분이 어려움)
				//next()는 커서를 다음으로 옮김. 갈 수 있으면 true, 없으면 false가 나옴
				while(rs.next()) {
					/*
					//컬럼 이름, 별명으로 가져오기
					int authorId = rs.getInt("id");//컬럼명, 별명 사용시 별명
					String authorName = rs.getString("author_name");
					String authorDesc = rs.getString("author_desc");
					*/
					//쿼리문의 select 순서로 가져오기
					
					int authorId = rs.getInt(1);//컬럼명, 별명 사용시 별명
					String authorName = rs.getString(2);
					String authorDesc = rs.getString(3);
					
					AuthorVo vo = new AuthorVo(authorId, authorName, authorDesc);
					authorList.add(vo);
					
					//System.out.println(authorId + "\t" + authorName + "\t" + authorDesc);
				}
				//리스트 전체 출력
				for(int i=0; i<authorList.size(); i++) {
					AuthorVo authorVo = authorList.get(i);
					System.out.println(authorVo.getAuthorId()+"," +authorVo.getAuthorName()+", "+authorVo.getAuthorDesc());
				}
				//첫번째 작가만 출력(0번 인덱스)
				System.out.println("첫번째 작가 정보 출력");
				System.out.println(authorList.get(0).getAuthorId() + ", " + authorList.get(1).getAuthorName() + ", " + authorList.get(1).getAuthorDesc());
				
				
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

