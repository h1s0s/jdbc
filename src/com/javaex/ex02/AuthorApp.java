package com.javaex.ex02;

import java.util.List;

public class AuthorApp {

	public static void main(String[] args) {
		//AuthorDao라는 클래스에 DML 메소드를 만들어 사용하기
		
		List<AuthorVo> authorList;
		AuthorDao authorDao = new AuthorDao();
		
		//작가 등록(insert)
		authorDao.authorInsert("이문열", "경북 영양");

		//작가 등록(insert)
		authorDao.authorInsert("박경리", "경상남도 통영");

		//작가 등록(insert)
		authorDao.authorInsert("유시민", "17대 국회의원");
		
		authorList = authorDao.authorSelect();
		for(int i=0; i<authorList.size(); i++) {
			AuthorVo vo = authorList.get(i);
			System.out.println(vo.getAuthorId() + ", " + vo.getAuthorName() + ", " + vo.getAuthorDesc());
		}
		System.out.println("--------------------------------------------------------------------------");
		
		//작가 수정(Update)
		authorDao.authorUpdate(2, "박경리(수정)", "경상남도 통영(수정)");
		
		authorList = authorDao.authorSelect();
		for(int i=0; i<authorList.size(); i++) {
			AuthorVo vo = authorList.get(i);
			System.out.println(vo.getAuthorId() + ", " + vo.getAuthorName() + ", " + vo.getAuthorDesc());
		}
		
		//작가 삭제(Delete)
		//authorDao.authorDelete(1);
		
	}
}
