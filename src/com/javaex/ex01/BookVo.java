package com.javaex.ex01;

public class BookVo {

	//필드
	private int bookId;
	private String bookTitle;
	private String bookPubs;
	private String bookPubDate;
	private int bookAuthorId;

	//생성자
	public BookVo() {
		
	}
	public BookVo(int bookId, String bookTitle, String bookPubs, String bookPubDate, int bookAuthorId) {
		this.bookId = bookId;
		this.bookTitle = bookTitle;
		this.bookPubs = bookPubs;
		this.bookPubDate = bookPubDate;
		this.bookAuthorId = bookAuthorId;
	}
	
	//메소드 gs
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public String getBookPubs() {
		return bookPubs;
	}
	public void setBookPubs(String bookPubs) {
		this.bookPubs = bookPubs;
	}
	public String getBookPubDate() {
		return bookPubDate;
	}
	public void setBookPubDate(String bookPubDate) {
		this.bookPubDate = bookPubDate;
	}
	public int getBookAuthorId() {
		return bookAuthorId;
	}
	public void setBookAuthorId(int bookAuthorId) {
		this.bookAuthorId = bookAuthorId;
	}

	//메소드 일반
	@Override
	public String toString() {
		return "BookVo [bookId=" + bookId + ", bookTitle=" + bookTitle + ", bookPubs=" + bookPubs + ", bookPubDate="
				+ bookPubDate + ", bookAuthorId=" + bookAuthorId + "]";
	}
}
