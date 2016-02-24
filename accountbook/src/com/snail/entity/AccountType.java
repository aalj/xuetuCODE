/**
 * AccountType.java
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
/**
 * ClassName:AccountType
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 *
 * @author   Stone
 * @version  
 * @since    Ver 1.1
 * @Date	 2016年1月27日		下午5:02:09
 *
 * @see 	 

 */
public class AccountType {
	/**
	 * 账单类型福父类型id
	 */
	private int accountTypeID = 0;
	/**
	 * 账单类型福父类型名字
	 */
	private String accountTypeNma = null;
	 public AccountType() {

		// 

	}
	public int getAccountTypeID() {
		return accountTypeID;
	}
	public void setAccountTypeID(int accountTypeID) {
		this.accountTypeID = accountTypeID;
	}
	public String getAccountTypeNma() {
		return accountTypeNma;
	}
	public void setAccountTypeNma(String accountTypeNma) {
		this.accountTypeNma = accountTypeNma;
	}
	public AccountType(int accountTypeID, String accountTypeNma) {
		super();
		this.accountTypeID = accountTypeID;
		this.accountTypeNma = accountTypeNma;
	}

}

