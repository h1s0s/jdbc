package com.javaex.ex03;

import java.util.List;

public class AuthorApp {

	public static void main(String[] args) {
		// 객체단위로 활용

		List<AuthorVo> list;
		AuthorDao authorDao = new AuthorDao();
		
		//작가 등록(insert)
		AuthorVo vo01 = new AuthorVo("이문열", "경북 영양");
		authorDao.authorInsert(vo01);
		
		AuthorVo vo02 = new AuthorVo("박경리", "경상남도 통영");
		authorDao.authorInsert(vo02);
		
		AuthorVo vo03 = new AuthorVo("유시민", "17대 국회의원");
		authorDao.authorInsert(vo03);
		
		System.out.println("---------------------------------");
		list = authorDao.authorSelect();
		for(int i=0; i<list.size(); i++) {
			AuthorVo vo = list.get(i);
			System.out.println(vo.getAuthorId() + ", " + vo.getAuthorName() + ", " + vo.getAuthorDesc());
		}
		System.out.println("---------------------------------");
		
		//작가 수정(Update)
		AuthorVo authorVo = new AuthorVo(2, "박경리(수정)", "경상남도 통영(수정)");
		authorDao.authorUpdate(authorVo);
		
		System.out.println("---------------------------------");
		list = authorDao.authorSelect();
		for(int i=0; i<list.size(); i++) {
			AuthorVo vo = list.get(i);
			System.out.println(vo.getAuthorId() + ", " + vo.getAuthorName() + ", " + vo.getAuthorDesc());
		}
		System.out.println("---------------------------------");
		
		//작가 삭제(Delete)
		authorDao.authorDelete(1);
		
		System.out.println("---------------------------------");
		list = authorDao.authorSelect();
		for(int i=0; i<list.size(); i++) {
			AuthorVo vo = list.get(i);
			System.out.println(vo.getAuthorId() + ", " + vo.getAuthorName() + ", " + vo.getAuthorDesc());
		}
		System.out.println("---------------------------------");
	}
}
