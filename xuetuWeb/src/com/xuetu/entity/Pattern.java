/**
 * Pattern.java
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
 * ClassName:Pattern<br/>
 * Function: 计划模式实体表<br/>
 *
 * @author   Stone
 * @version  
 * @since    Ver 1.1
 * @Date	 2016年2月19日		下午11:36:45
 *
 * @see 	 

 */
public class Pattern {
	private int patternID = 0;
	private String pattrenText = null;
	/**
	 * 
	 * getPatternID:(得到计划模式的ID)<br/>
	
	 *
	 * @param  @return    设定文件
	 * @return int    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public int getPatternID() {
		return patternID;
	}
	/**
	 * 
	 * setPatternID:(设置计划模式的ID)<br/>
	
	 *
	 * @param  @param patternID    设定文件
	 * @return void    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public void setPatternID(int patternID) {
		this.patternID = patternID;
	}
	/**
	 * 
	 * getPattrenText:(得到计划模式的描述)<br/>
	
	 *
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public String getPattrenText() {
		return pattrenText;
	}
	/**
	 * 
	 * setPattrenText:(设置计划模式的描述)<br/>
	
	 *
	 * @param  @param pattrenText    设定文件
	 * @return void    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public void setPattrenText(String pattrenText) {
		this.pattrenText = pattrenText;
	}
	public Pattern(int patternID, String pattrenText) {
		this.patternID = patternID;
		this.pattrenText = pattrenText;
	}

}

