/**
 * MD5Utils.java
 * com.librarybooksearch.utils
 *
 * Function�� TODO 
 *
 *   ver     date      		author
 * ��������������������������������������������������������������������
 *   		 2015��12��5�� 		Stone_A
 *
 * Copyright (c) 2015, TNT All Rights Reserved.
*/

package com.xuetu.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * ClassName:MD5Utils
 * <p>
 * Function: ����MD5����
 * Reason:	 TODO ADD REASON
 *
 * @author   Stone_A
 * @version  
 * @since    Ver 1.1
 * @Date	 2015��12��5��		����3:16:10
 *
 * @see 	 

 */
public class MD5Utils {

	public static  String digestMD5(String password){
		
		try {
			MessageDigest  digest = MessageDigest.getInstance("MD5");
			byte [] by = digest.digest(password.getBytes());
			StringBuffer buffer = new StringBuffer();
			for (byte b : by) {
				int result = b&0xff;
				String hexString = Integer.toHexString(result);
				if(hexString.length()<2){
					buffer.append("0");
				}
				buffer.append(hexString);
			}
			
			String string = buffer.toString();
			
			return string ;
		} catch (NoSuchAlgorithmException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
		
		
		
	}
	
}

