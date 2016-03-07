/**
 * StreamUtils.java
 * com.aalj40.safeapp.utilas
 *
 * Function�� TODO 
 *
 *   ver     date      		author
 * ��������������������������������������������������������������������
 *   		 2015��10��2�� 		view
 *
 * Copyright (c) 2015, TNT All Rights Reserved.
*/

package com.xuetu.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;

/**
 * ClassName:StreamUtils
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 *
 * @author   view
 * @version  
 * @since    Ver 1.1
 * @Date	 2015��10��2��		����4:20:34
 *
 * @see 	 

 */
public class StreamUtils {
	public static String parserStream(InputStream is) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		//д����
		StringWriter sw = new StringWriter();
		String str = null;
		while ((str=br.readLine())!=null ){
			sw.write(str);
		}
		br.close();
		return sw.toString();
	}
}

