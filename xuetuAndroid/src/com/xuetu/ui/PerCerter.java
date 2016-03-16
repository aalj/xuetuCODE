/**
 * PerCerter.java
 * com.librarybooksearch.ui
 *
 * Function�� TODO 
 *
 *   ver     date      		author
 * ��������������������������������������������������������������������
 *   		 2015��11��6�� 		view
 *
 * Copyright (c) 2015, TNT All Rights Reserved.
*/

package com.xuetu.ui;

import java.io.Serializable;

import com.xuetu.R;
import com.xuetu.entity.Student;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * ClassName:PerCerter
 * Function: ��������ҳ��
 * Reason:	 TODO ADD REASON
 *
 * @author   view
 * @version  
 * @since    Ver 1.1
 * @Date	 2015��11��6��		����5:34:26
 *
 * @see 	 

 */
public class PerCerter extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_profile);
		//登录成功传值出来
		Intent intent =this.getIntent();
		Student student = (Student) intent.getSerializableExtra("KEY");
	}

}

