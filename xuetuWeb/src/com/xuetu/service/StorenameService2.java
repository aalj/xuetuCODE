
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
 * Reason: TODO ADD REASON<br/>
 *
 * @author Stone
 * @version
 * @since Ver 1.1
 * @Date 2016 2016年2月21日 下午3:32:47
 *
 * @see
 */
public class StorenameService2 {
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
	 * verificationName:(通过ID得到店家的信息)<br/>
	 *
	 * @param @param
	 *            sto_id
	 * @param @return
	 *            设定文件
	 * @return StoreName DOM对象
	 * @throws @since
	 *             CodingExample Ver 1.1
	 */
	public StoreName verificationName(int sto_id) {
		StoreNameDao2 dao = new StoreNameDao2();
		return dao.getStoreNameById(sto_id);

	}

	/**
	 * 
	 * getStoreName:(通过查询数据库得到数据对像)<br/>
	 *
	 * 
	 * @param @param
	 *            name
	 * @param @param
	 *            pwd
	 * @param @return
	 *            设定文件
	 * @return StoreName DOM对象
	 * @throws @since
	 *             CodingExample Ver 1.1
	 */
	public StoreName getStoreName(String name, String pwd) {
		if (!"".equals(name)  && !"".equals(pwd)) {
			StoreNameDao2 dao = new StoreNameDao2();
			StoreName storeName = verificationNamePwd(name);
			if (pwd.equals(storeName.getStoPwd())) {
				return dao.getStoreNameByName(name);
			}
		}

		return null;

	}
	/**
	 * 
	 * changePwd:(通过ID修改密码)<br/>
	 *
	 * @param  @param sro_ID    设定文件
	 * @return void    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public boolean changePwd(int attribute,String reNewPwd){
		StoreNameDao2 dao2 = new StoreNameDao2();
		return dao2.changeStoNamePWD(attribute,reNewPwd);
		
	}

	
	
	

}