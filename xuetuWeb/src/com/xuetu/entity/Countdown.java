/**
 * Countdown.java
 * com.xuetu.entity
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016年2月20日 		Stone
 *
 * Copyright (c) 2016, TNT All Rights Reserved.
*/

package com.xuetu.entity;

import java.util.Date;

/**
 * ClassName:Countdown<br/>
 * Function: 倒计时表实体类<br/>
 *
 * @author Stone
 * @version
 * @since Ver 1.1
 * @Date 2016年2月20日 上午9:14:14
 *
 * @see
 * 
 */
public class Countdown {
	private int codoID = 0;
	private Date codoTime = null;
	private String codoText = null;

	public Countdown() {

		//

	}

	public Countdown(int codoID, Date codoTime, String codoText) {
		super();
		this.codoID = codoID;
		this.codoTime = codoTime;
		this.codoText = codoText;
	}
	/**
	 * 
	 * getCodoID:(得到该条倒计时的ID)<br/>
	
	 *
	 * @param  @return    设定文件
	 * @return int    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public int getCodoID() {
		return codoID;
	}
/**
 * 
 * setCodoID:(设置该条记录倒计时的ID)<br/>

 *
 * @param  @param codoID    设定文件
 * @return void    DOM对象
 * @throws 
 * @since  CodingExample　Ver 1.1
 */
	public void setCodoID(int codoID) {
		this.codoID = codoID;
	}
/**
 * 
 * getCodoTime:(得到倒计时截至时间)<br/>

 *
 * @param  @return    设定文件
 * @return Date    DOM对象
 * @throws 
 * @since  CodingExample　Ver 1.1
 */
	public Date getCodoTime() {
		return codoTime;
	}
	/**
	 * 
	 * setCodoTime:(设置到家死截至时间)<br/>
	
	 *
	 * @param  @param codoTime    设定文件
	 * @return void    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public void setCodoTime(Date codoTime) {
		this.codoTime = codoTime;
	}
/**
 * 
 * getCodoText:(得到倒计时描述)<br/>

 *
 * @param  @return    设定文件
 * @return String    DOM对象
 * @throws 
 * @since  CodingExample　Ver 1.1
 */
	public String getCodoText() {
		return codoText;
	}
/**
 * 
 * setCodoText:(设置倒计时描述)<br/>

 *
 * @param  @param codoText    设定文件
 * @return void    DOM对象
 * @throws 
 * @since  CodingExample　Ver 1.1
 */
	public void setCodoText(String codoText) {
		this.codoText = codoText;
	}

}
