/**
 * FamilyService.java
 * com.snail.dao.servlet
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016年1月28日 		asus
 *
 * Copyright (c) 2016, TNT All Rights Reserved.
*/

package com.snail.dao.servlet;

import com.snail.dao.Family;
import com.snail.dao.FamilyDao;

/**
 * ClassName:FamilyService
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 *
 * @author   asus
 * @version  
 * @since    Ver 1.1
 * @Date	 2016年1月28日		下午2:04:40
 *
 * @see 	 

 */
public class FamilyService {
	FamilyDao dao = new FamilyDao();
	
	public void save(Family family)
	{
		dao.faAdd(family);
	}
}

