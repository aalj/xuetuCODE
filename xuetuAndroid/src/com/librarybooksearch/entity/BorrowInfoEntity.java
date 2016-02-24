/**
 * BorrowInfo.java
 * com.librarybooksearch.entity
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2015年11月6日 		view
 *
 * Copyright (c) 2015, TNT All Rights Reserved.
*/

package com.librarybooksearch.entity;
/**
 * ClassName:BorrowInfo
 * Function: 借阅图书的实体类用于描述借阅书籍的详细信息
 * Reason:	 TODO ADD REASON
 *
 * @author   view
 * @version  
 * @since    Ver 1.1
 * @Date	 2015年11月6日		下午8:55:08
 *
 * @see 	 

 */
public class BorrowInfoEntity {
	private String bookname = null;
	private String booktime = null;
	private String bookloca = null;
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public String getBooktime() {
		return booktime;
	}
	public void setBooktime(String booktime) {
		this.booktime = booktime;
	}
	public String getBookloca() {
		return bookloca;
	}
	public void setBookloca(String bookloca) {
		this.bookloca = bookloca;
	}
	public BorrowInfoEntity(String bookname, String booktime, String bookloca) {
		super();
		this.bookname = bookname;
		this.booktime = booktime;
		this.bookloca = bookloca;
	}

}

