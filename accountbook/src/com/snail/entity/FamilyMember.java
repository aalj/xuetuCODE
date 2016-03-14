/**
 * FamilyMember.java
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
 * ClassName:FamilyMember
 * Function: 家庭成员实体
 * Reason:	 TODO ADD REASON
 *
 * @author   Stone
 * @version  
 * @since    Ver 1.1
 * @Date	 2016年1月27日		下午4:03:06
 *
 * @see 	 

 */
public class FamilyMember {
	/**
	 * 家庭成员ID
	 */
	private int familyMemberID = 0;
	/**
	 * 家庭成员名字
	 */
	private String familyMemberName = null;
	/**
	 * 家庭成员权限表<br/>
	 * 表示父亲，母亲，哥哥，弟弟
	 */
	private int familyMemberPermission =0;
	/**
	 * 成员密码
	 */
	private String pwd = null;

	public int getFamilyMemberID() {
		return familyMemberID;
	}

	public void setFamilyMemberID(int familyMemberID) {
		this.familyMemberID = familyMemberID;
	}

	public String getFamilyMemberName() {
		return familyMemberName;
	}

	public void setFamilyMemberName(String familyMemberName) {
		this.familyMemberName = familyMemberName;
	}

	public int getFamilyMemberPermission() {
		return familyMemberPermission;
	}

	public void setFamilyMemberPermission(int familyMemberPermission) {
		this.familyMemberPermission = familyMemberPermission;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public FamilyMember(int familyMemberID, String familyMemberName, int familyMemberPermission, String pwd) {
		super();
		this.familyMemberID = familyMemberID;
		this.familyMemberName = familyMemberName;
		this.familyMemberPermission = familyMemberPermission;
		this.pwd = pwd;
	}
	

}

