package com.javaex.ex01;

public class BookAllVo {

	//필드
	private int bookAllId;
	private String bookAllTitle;
	private String bookAllPubs;
	private String bookAllPubDate;
	private int bookAllAuthorId;
	private String bookAllAuthorName;
	private String bookAllAuthorDesc;


	//생성자
	public BookAllVo() {
		
	}
	public BookAllVo(int bookAllId, String bookAllTitle, String bookAllPubs, String bookAllPubDate, int bookAllAuthorId,
			String bookAllAuthorName, String bookAllAuthorDesc) {
		this.bookAllId = bookAllId;
		this.bookAllTitle = bookAllTitle;
		this.bookAllPubs = bookAllPubs;
		this.bookAllPubDate = bookAllPubDate;
		this.bookAllAuthorId = bookAllAuthorId;
		this.bookAllAuthorName = bookAllAuthorName;
		this.bookAllAuthorDesc = bookAllAuthorDesc;
	}
	
	//메소드 g/s
	public int getBookAllId() {
		return bookAllId;
	}
	public void setBookAllId(int bookAllId) {
		this.bookAllId = bookAllId;
	}
	public String getBookAllTitle() {
		return bookAllTitle;
	}
	public void setBookAllTitle(String bookAllTitle) {
		this.bookAllTitle = bookAllTitle;
	}
	public String getBookAllPubs() {
		return bookAllPubs;
	}
	public void setBookAllPubs(String bookAllPubs) {
		this.bookAllPubs = bookAllPubs;
	}
	public String getBookAllPubDate() {
		return bookAllPubDate;
	}
	public void setBookAllPubDate(String bookAllPubDate) {
		this.bookAllPubDate = bookAllPubDate;
	}
	public int getBookAllAuthorId() {
		return bookAllAuthorId;
	}
	public void setBookAllAuthorId(int bookAllAuthorId) {
		this.bookAllAuthorId = bookAllAuthorId;
	}
	public String getBookAllAuthorName() {
		return bookAllAuthorName;
	}
	public void setBookAllAuthorName(String bookAllAuthorName) {
		this.bookAllAuthorName = bookAllAuthorName;
	}
	public String getBookAllAuthorDesc() {
		return bookAllAuthorDesc;
	}
	public void setBookAllAuthorDesc(String bookAllAuthorDesc) {
		this.bookAllAuthorDesc = bookAllAuthorDesc;
	}

	//메소드 일반
	@Override
	public String toString() {
		return "BookAllVo [bookAllId=" + bookAllId + ", bookAllTitle=" + bookAllTitle + ", bookAllPubs=" + bookAllPubs
				+ ", bookAllPubDate=" + bookAllPubDate + ", bookAllAuthorId=" + bookAllAuthorId + ", bookAllAuthorName="
				+ bookAllAuthorName + ", bookAllAuthorDesc=" + bookAllAuthorDesc + "]";
	}
}
