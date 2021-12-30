package com.javaex.ex04;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao {

	// ex04. 공통변수 빼기

	// 필드
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	// 생성자
	public AuthorDao() {
	}
	// 메소드 gs

	// 메소드 일반
	public void getConnection() {
		// 1. JDBC 드라이버 (Oracle) 로딩
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pw);

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	public void close() {
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

	public void authorInsert(AuthorVo vo01) {
		getConnection();
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// 3.1문자열 만들기
			String query = "";
			query += " insert into author ";
			query += " values (seq_author_id.nextval, ?, ?) ";
			// System.out.println(query);

			// 3.2 문자열을 쿼리문으로 만들기
			pstmt = conn.prepareStatement(query);

			// 3.3 바인딩 (?에 데이터를 넣어주는 작업)
			pstmt.setString(1, vo01.getAuthorName());
			pstmt.setString(2, vo01.getAuthorDesc());

			// 3.4 실행
			int count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + " 건이 저장되었습니다.(작가)");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close();

	}

	public void authorUpdate(AuthorVo authorVo) {
		getConnection();
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " update  author ";
			query += " set     author_name= ?, ";
			query += " author_desc = ? ";
			query += " where   author_id = ? ";
			// System.out.println(query);

			// 3.2 문자열을 쿼리문으로 만들기
			pstmt = conn.prepareStatement(query);

			// 3.3 바인딩
			pstmt.setString(1, authorVo.getAuthorName());
			pstmt.setString(2, authorVo.getAuthorDesc());
			pstmt.setInt(3, authorVo.getAuthorId());

			// 3.4 실행
			int count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + " 건이 수정되었습니다.(작가)");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();
	}

	public void authorDelete(int authorId) {
		getConnection();
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " delete  from author ";
			query += " where   author_id = ? ";

			// 3.2 문자열을 쿼리문으로 만들기
			pstmt = conn.prepareStatement(query);

			// 3.3 바인딩
			pstmt.setInt(1, authorId);

			// 3.4 실행
			int count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + " 건이 삭제되었습니다.(작가)");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close();

	}

	public List<AuthorVo> authorSelect() {
		List<AuthorVo> authorList = new ArrayList<AuthorVo>();
		getConnection();
		try {
			// 3.1 문자열 만들기
			String query = "";
			query += " select author_id, ";
			query += "        author_name, ";
			query += "        author_desc ";
			query += " from   author ";

			// 3.2 문자열을 쿼리문으로 만들기
			pstmt = conn.prepareStatement(query);

			// 3.3 실행
			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {

				int authorId = rs.getInt(1);
				String authorName = rs.getString(2);
				String authorDesc = rs.getString(3);

				AuthorVo vo = new AuthorVo(authorId, authorName, authorDesc);
				authorList.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close();
		return authorList;
	}
}