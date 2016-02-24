/**
 * Account.java
 * com.snail.entity
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016年1月27日 		Stone
 *
 * Copyright (c) 2016, TNT All Rights Reserved.
*/

package com.snail.entity;

import java.util.Date;

/**
 * ClassName:Account
 * Function: 账单实体
 * Reason:	 TODO ADD REASON
 *
 * @author   Stone
 * @version  
 * @since    Ver 1.1
 * @Date	 2016年1月27日		下午4:01:13
 *
 * @see 	 

 */
public class Account {
	/**
	 * 账单id
	 */
	private int accountID=0;
	/**
	 * 所属成员id
	 * 
	 */
	private int memberID=0;
	/**
	 * 账单类型id
	 */
	
	private int accountType=0;
	/**
	 * 收支类型ID
	 */
	private int incomeID = 0;
	/**
	 * 账单产生时间
	 */
	private Date incomeDate = null;
	/**
	 * 账单金额数量
	 */
	private double money = 0;
	/**
	 * 账单的描述
	 */
	private String text = null;
	public Account() {

		

	}
	public int getAccountID() {
		return accountID;
	}
	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}
	public int getMemberID() {
		return memberID;
	}
	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}
	public int getAccountType() {
		return accountType;
	}
	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}
	public int getIncomeID() {
		return incomeID;
	}
	public void setIncomeID(int incomeID) {
		this.incomeID = incomeID;
	}
	public Date getIncomeDate() {
		return incomeDate;
	}
	public void setIncomeDate(Date incomeDate) {
		this.incomeDate = incomeDate;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Account(int accountID, int memberID, int accountType, int incomeID, Date incomeDate, double money,
			String text) {
		super();
		this.accountID = accountID;
		this.memberID = memberID;
		this.accountType = accountType;
		this.incomeID = incomeID;
		this.incomeDate = incomeDate;
		this.money = money;
		this.text = text;
	}
	
	
	
	

}

