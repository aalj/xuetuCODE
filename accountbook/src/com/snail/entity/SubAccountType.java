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
public class SubAccountType {
	/**
	 * 账单类型福子类型id
	 */
	private int subAccountTypeID = 0;
	/**
	 * 账单类型福子类型名字
	 */
	private String subAccountTypeNma = null;
	public SubAccountType() {

		// 

	}
	public int getSubAccountTypeID() {
		return subAccountTypeID;
	}
	public void setSubAccountTypeID(int subAccountTypeID) {
		this.subAccountTypeID = subAccountTypeID;
	}
	public String getSubAccountTypeNma() {
		return subAccountTypeNma;
	}
	public void setSubAccountTypeNma(String subAccountTypeNma) {
		this.subAccountTypeNma = subAccountTypeNma;
	}
	public int getAccountTypeID() {
		return accountTypeID;
	}
	public void setAccountTypeID(int accountTypeID) {
		this.accountTypeID = accountTypeID;
	}
	public SubAccountType(int subAccountTypeID, String subAccountTypeNma, int accountTypeID) {
		super();
		this.subAccountTypeID = subAccountTypeID;
		this.subAccountTypeNma = subAccountTypeNma;
		this.accountTypeID = accountTypeID;
	}
	/**
	 * 账单类型福父类型id
	 */
	private int accountTypeID = 0;
	 


}

