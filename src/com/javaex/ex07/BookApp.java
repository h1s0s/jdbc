package com.javaex.ex07;

import java.util.List;

public class BookApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<BookVo> BookList;
		BookDao bookDao = new BookDao();

		// 책 등록(insert)
		BookVo vo01 = new BookVo("우리들의 일그러진 영웅", "다림", "1998-02-22", 1);
		bookDao.bookInsert(vo01);

		// 책 등록(insert)
		BookVo vo02 = new BookVo("삼국지", "민음사", "2002-03-01", 1);
		bookDao.bookInsert(vo02);

		// 책 출력(select)
		System.out.println("--------------------------------------------------------------------------------------");
		BookList = bookDao.BookSelect();
		for (int i = 0; i < BookList.size(); i++) {
			BookVo vo = BookList.get(i);
			System.out.println(vo.getBookId() + ", " + vo.getTitle() + ", " + vo.getPubs() + ", " + vo.getPubDate() + ", " + vo.getAuthorId());
		}
		System.out.println("--------------------------------------------------------------------------------------");
		// 책 수정(Update)
		BookVo updatebook = new BookVo(2, "삼국지(수정)", "민음사(수정)", "2002-03-01");
		bookDao.BookUpdate(updatebook);

		// 책 삭제(Delete)
		bookDao.BookDelete(1);

		// 책 출력(select)
		System.out.println("--------------------------------------------------------------------------------------");
		BookList = bookDao.BookSelect();
		for (int i = 0; i < BookList.size(); i++) {
			BookVo vo = BookList.get(i);
			System.out.println(vo.getBookId() + ", " + vo.getTitle() + ", " + vo.getPubs() + ", " + vo.getPubDate());
		System.out.println("--------------------------------------------------------------------------------------");
		}

	}

}
