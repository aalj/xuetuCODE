/**
 * FragmentViewPageAdapter.java
 * com.librarybooksearch.baseAdapter
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016年3月7日 		Stone
 *
 * Copyright (c) 2016, TNT All Rights Reserved.
*/

package com.xuetu.baseAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * ClassName:FragmentViewPageAdapter
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 *
 * @author   Stone
 * @version  
 * @since    Ver 1.1
 * @Date	 2016年3月7日		上午12:04:25
 *
 * @see 	 

 */
public class FragmentViewPageAdapter extends FragmentPagerAdapter {
	Fragment[] fm_shuzu = null;
	public FragmentViewPageAdapter(FragmentManager fm,Fragment[] fm_shuzu ) {
		super(fm);
		this.fm_shuzu =  fm_shuzu ;
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public Fragment getItem(int arg0) {

		return fm_shuzu[arg0];

	}

	@Override
	public int getCount() {

		return fm_shuzu.length;

	}

}

