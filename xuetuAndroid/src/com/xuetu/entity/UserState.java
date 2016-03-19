/**
 * UserState.java
 * com.xuetu.entity
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016年2月19日 		Stone
 *
 * Copyright (c) 2016, TNT All Rights Reserved.
*/

package com.xuetu.entity;
/**
 * ClassName:UserState<br/>
 * Function: 优惠券使用状态的实体表<br/>  g
 * Reason:	 TODO ADD REASON<br/>
 *
 * @author   Stone
 * @version  
 * @since    Ver 1.1
 * @Date	 2016年2月19日		下午11:19:09
 *
 * @see 	 

 */
public class UserState {
	private int ustaID=0;
	private String ustaName=null;
	public UserState() {

		// 

	}
	/**
	 * 
	 * getUstaID:(得到使用状态的ID)<br/>
	
	 *
	 * @param  @return    设定文件
	 * @return int    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public int getUstaID() {
		return ustaID;
	}
	/**
	 * 
	 * setUstaID:(设置使用状态的ID)<br/>
	
	 *
	 * @param  @param ustaID    设定文件
	 * @return void    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public void setUstaID(int ustaID) {
		this.ustaID = ustaID;
	}
	/**
	 * 
	 * getUstaName:(得到使用状态的名称)<br/>
	
	 *
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public String getUstaName() {
		return ustaName;
	}
	/**
	 * 
	 * setUstaName:(设置使用状态的名称)<br/>
	
	 *
	 * @param  @param ustaName    设定文件
	 * @return void    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public void setUstaName(String ustaName) {
		this.ustaName = ustaName;
	}
	public UserState(int ustaID, String ustaName) {
		this.ustaID = ustaID;
		this.ustaName = ustaName;
	}

}

