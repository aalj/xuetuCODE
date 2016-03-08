/**
 * BorrowInfo.java
 * com.librarybooksearch.entity
 *
 * Function�� TODO 
 *
 *   ver     date      		author
 * ��������������������������������������������������������������������
 *   		 2015��11��6�� 		view
 *
 * Copyright (c) 2015, TNT All Rights Reserved.
*/

package com.xuetu.entity;
/**
 * ClassName:BorrowInfo
 * Function: ����ͼ���ʵ�����������������鼮����ϸ��Ϣ
 * Reason:	 TODO ADD REASON
 *
 * @author   view
 * @version  
 * @since    Ver 1.1
 * @Date	 2015��11��6��		����8:55:08
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

