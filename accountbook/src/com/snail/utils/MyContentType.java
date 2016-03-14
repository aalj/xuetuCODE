/**
 * ToUtf8.java
 * com.snail.utils
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016年1月27日 		Stone
 *
 * Copyright (c) 2016, TNT All Rights Reserved.
*/

package com.snail.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName:ToUtf8
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 *
 * @author   Stone
 * @version  
 * @since    Ver 1.1
 * @Date	 2016年1月27日		下午9:46:45
 *
 * @see 	 

 */
public class MyContentType {
	public static void  toUtf8(HttpServletRequest request, HttpServletResponse response){
		
		response.setContentType("text/html;charset=utf-8");
		
	}

}

