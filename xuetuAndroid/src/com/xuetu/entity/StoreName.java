/**
 * StoreName.java
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
 * ClassName:StoreName<br/>
 * Function: 店家实体表<br/>
 * Reason:	 TODO ADD REASON<br/>
 *
 * @author   Stone
 * @version  
 * @since    Ver 1.1
 * @Date	 2016年2月19日		下午11:24:04
 *
 * @see 	 

 */
public class StoreName {
	private int  stoID = 0;
	private String stoUserName = null;
	
	private String stoTel = null;
	private String stoAddress = null;
	private String stoName = null;
	private String stoIntroduction = null;
	private String stoImg = null;
	private String stoPwd = null;
	/**
	 * 
	 * getStoID:(得到店家对象的ID)<br/>
	
	 *
	 * @param  @return    设定文件
	 * @return int    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public int getStoID() {
		return stoID;
	}
	/**
	 * 
	 * setStoID:(设置店家对象的ID)<br/>
	
	 *
	 * @param  @param stoID    设定文件
	 * @return void    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public void setStoID(int stoID) {
		this.stoID = stoID;
	}
	/**
	 * 
	 * getStoUserName:(得到店家用户名，用于登录使用)<br/>
	
	 *
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public String getStoUserName() {
		return stoUserName;
	}
	/**
	 * 
	 * setStoUserName:(设置店家的用户名)<br/>
	
	 *
	 * @param  @param stoUserName    设定文件
	 * @return void    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public void setStoUserName(String stoUserName) {
		this.stoUserName = stoUserName;
	}
	/**
	 * 
	 * getStoTel:(得到店家的电话)<br/>
	
	 *
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public String getStoTel() {
		return stoTel;
	}
	/**
	 * 
	 * setStoTel:(设置店家的电话)<br/>
	
	 *
	 * @param  @param stoTel    设定文件
	 * @return void    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public void setStoTel(String stoTel) {
		this.stoTel = stoTel;
	}
	/**
	 * 
	 * getStoAddress:(得到店家的店铺地址)<br/>
	
	 *
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public String getStoAddress() {
		return stoAddress;
	}
	/**
	 * 
	 * setStoAddress:(设置店家的店铺地址)<br/>
	
	 *
	 * @param  @param stoAddress    设定文件
	 * @return void    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public void setStoAddress(String stoAddress) {
		this.stoAddress = stoAddress;
	}
	/**
	 * 
	 * getStiName:(得到店家的店铺名称)<br/>
	
	 *
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public String getStoName() {
		return stoName;
	}
	/**
	 * 
	 * setStiName:(设置店家的店铺名称)<br/>
	
	 *
	 * @param  @param stiName    设定文件
	 * @return void    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public void setStoName(String stoName) {
		this.stoName = stoName;
	}
	/**
	 * 
	 * getStoIntroduction:(得到店家的店铺简介)<br/>
	
	 *
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public String getStoIntroduction() {
		return stoIntroduction;
	}
	/**
	 * 
	 * setStoIntroduction:(设置店家店铺的简介)<br/>
	
	 *
	 * @param  @param stoIntroduction    设定文件
	 * @return void    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public void setStoIntroduction(String stoIntroduction) {
		this.stoIntroduction = stoIntroduction;
	}
	/**
	 * 
	 * getStoIma:(得到店家的店铺图片)<br/>
	
	 *
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public String getStoImg() {
		return stoImg;
	}
	/**
	 * 
	 * setStoIma:(设置店家的店铺名称)<br/>
	
	 *
	 * @param  @param stoIma    设定文件
	 * @return void    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public void setStoImg(String stoImg) {
		this.stoImg = stoImg;
	}
	/**
	 * 
	 * getStoPwd:(得到店家的登录密码)<br/>
	
	 *
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public String getStoPwd() {
		return stoPwd;
	}
	/**
	 * 
	 * setStoPwd:(设置店家的登录密码)<br/>
	
	 *
	 * @param  @param stoPwd    设定文件
	 * @return void    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public void setStoPwd(String stoPwd) {
		this.stoPwd = stoPwd;
	}
	public StoreName(){
		
	}
	public StoreName(int stoID, String stoUserName, String stoTel, String stoAddress, String stoName,
			String stoIntroduction, String stoImg, String stoPwd) {
		this.stoID = stoID;
		this.stoUserName = stoUserName;
		this.stoTel = stoTel;
		this.stoAddress = stoAddress;
		this.stoName = stoName;
		this.stoIntroduction = stoIntroduction;
		this.stoImg = stoImg;
		this.stoPwd = stoPwd;
	}

}

