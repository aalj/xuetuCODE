
package com.xuetu.service;


import java.util.List;

import com.xuetu.dao.StoreNameDao2;
import com.xuetu.entity.Coupon;
import com.xuetu.entity.StoreName;
/**
 * 
 * ClassName:StorenameService2<br/>
 * 
 * Function: TODO ADD FUNCTION<br/>
 * 
 * Reason:	 TODO ADD REASON<br/>
 *
 * @author   Stone
 * @version  
 * @since    Ver 1.1
 * @Date	 2016	2016年2月21日		下午3:32:47
 *
 * @see
 */
public class StorenameService2{
	/**
	 * 
	 * verificationNamePwd:(通过用户名查询数据库得到对应 的数据对象)<br/>
	 *
	 * 
	 * @param @param
	 *            name
	 * @param @return
	 *            设定文件
	 * @return StoreName DOM对象
	 * @throws @since
	 *             CodingExample Ver 1.1
	 */
	private StoreName verificationNamePwd(String name) {
		StoreNameDao2 dao = new StoreNameDao2();
		return dao.getStoreNameByName(name);

	}


	/**
	 * 
	 * getStoreName:(通过查询数据库得到数据对像)<br/>
	
	 *
	 * @param  @param name
	 * @param  @param pwd
	 * @param  @return    设定文件
	 * @return StoreName    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public StoreName getStoreName(String name, String pwd) {
		StoreNameDao2 dao = new StoreNameDao2();
		StoreName storeName = verificationNamePwd(name);
		if (pwd.equals(storeName.getStoPwd())) {
			return dao.getStoreNameByName(name);
		}

		return null;

	}
	
	
	
	
	
	
}