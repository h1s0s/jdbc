package com.javaex.ex08;

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

	public void authorInsert(AuthorVo vo01) {
		getConnection();
		try {
			String query = "";
			query += " insert into author ";
			query += " values (seq_author_id.nextval, ?, ?) ";

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, vo01.getAuthorName());
			pstmt.setString(2, vo01.getAuthorDesc());

			int count = pstmt.executeUpdate();

			System.out.println(count + " 건이 저장되었습니다.(작가)");
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close();
	}

	public void authorUpdate(AuthorVo authorVo) {
		getConnection();
		try {
			String query = "";
			query += " update  author ";
			query += " set     author_name= ?, ";
			query += " author_desc = ? ";
			query += " where   author_id = ? ";

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, authorVo.getAuthorName());
			pstmt.setString(2, authorVo.getAuthorDesc());
			pstmt.setInt(3, authorVo.getAuthorId());

			int count = pstmt.executeUpdate();

			System.out.println(count + " 건이 수정되었습니다.(작가)");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close();
	}

	public void authorDelete(int authorId) {
		getConnection();
		try {
			String query = "";
			query += " delete  from author ";
			query += " where   author_id = ? ";

			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, authorId);

			int count = pstmt.executeUpdate();

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
			String query = "";
			query += " select author_id, ";
			query += "        author_name, ";
			query += "        author_desc ";
			query += " from   author ";

			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();

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