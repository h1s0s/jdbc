package com.javaex.ex08;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 작가dao 추가
		AuthorDao authorDao = new AuthorDao();

		// 작가 추가
		AuthorVo a01 = new AuthorVo("김문열", "경북 영양");
		authorDao.authorInsert(a01);

		AuthorVo a02 = new AuthorVo("박경리", "경상남도 통영");
		authorDao.authorInsert(a02);

		AuthorVo a03 = new AuthorVo("유시민", "17대 국회의원");
		authorDao.authorInsert(a03);

		AuthorVo a04 = new AuthorVo("기안84", "기안동에서 산 84년생");
		authorDao.authorInsert(a04);

		AuthorVo a05 = new AuthorVo("강풀", "온라인 만화가 1세대");
		authorDao.authorInsert(a05);

		AuthorVo a06 = new AuthorVo("김영하", "알쓸신잡");
		authorDao.authorInsert(a06);

		AuthorVo a07 = new AuthorVo("이고잉", "개발자");
		authorDao.authorInsert(a07);

		// 책dao 추가
		BookDao bookDao = new BookDao();
		List<BookVo> bookList = new ArrayList<BookVo>();

		// 책 등록(insert)
		BookVo vo01 = new BookVo("우리들의 일그러진 영웅", "다림", "1998-02-22", 1);
		bookDao.bookInsert(vo01);

		BookVo vo02 = new BookVo("삼국지", "민음사", "2002-03-01", 1);
		bookDao.bookInsert(vo02);

		BookVo vo03 = new BookVo("토지", "마로니에 북스", "2012-08-15", 2);
		bookDao.bookInsert(vo03);

		BookVo vo04 = new BookVo("자바프로그래밍 입문", "위키북스", "2015-04-01", 7);
		bookDao.bookInsert(vo04);

		BookVo vo05 = new BookVo("패션왕", "중앙북스(books)", "2012-02-22", 4);
		bookDao.bookInsert(vo05);

		BookVo vo06 = new BookVo("순정만화", "재미주의", "2011-08-03", 5);
		bookDao.bookInsert(vo06);

		BookVo vo07 = new BookVo("오직두사람", "문학동네", "2017-05-04", 6);
		bookDao.bookInsert(vo07);

		BookVo vo08 = new BookVo("26년", "재미주의", "2012-02-04", 5);
		bookDao.bookInsert(vo08);

		// 문자열 입력
		Scanner sc = new Scanner(System.in);
		System.out.println("검색할 문자를 입력해 주세요");
		String put = sc.nextLine();

		// 책 출력(select)
		System.out.println("--------------------------------------------------------------------------------------");
		bookList = bookDao.bookSearch(put);
		for (int i = 0; i < bookList.size(); i++) {
			BookVo vo = bookList.get(i);
			System.out.println(vo.getBookId() + ", " + vo.getTitle() + ", " + vo.getPubs() + ", " + vo.getPubDate()
					+ ", " + vo.getAuthorName());
		}
		System.out.println("--------------------------------------------------------------------------------------");

		sc.close();
	}

}
