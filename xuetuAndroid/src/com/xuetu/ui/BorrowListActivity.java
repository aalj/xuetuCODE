/**
 * BorrowList_Activity.java
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

import java.util.ArrayList;
import java.util.List;

import com.xuetu.R;
import com.xuetu.adapter.BorrowListBaseAdapter;
import com.xuetu.entity.BorrowInfoEntity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * ClassName:BorrowList_Activity
 * Function: ͼ������б�
 * Reason:	 TODO ADD REASON
 *
 * @author   view
 * @version  
 * @since    Ver 1.1
 * @Date	 2015��11��6��		����8:18:30
 *
 * @see 	 

 */
public class BorrowListActivity extends Activity {
	List<BorrowInfoEntity> list = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.borrow_list_activity);
		InitView();
		
	}
	/**
	 * 
	 * InitView:(ҳ���ʼ���ķ���)
	 *
	 * @param      �趨�ļ�
	 * @return void    DOM����
	 * @throws 
	 * @since  CodingExample��Ver 1.1
	 */
	private void InitView() {
		list = new ArrayList<BorrowInfoEntity>();
		for (int i = 0; i <50; i++) {
			list.add(new BorrowInfoEntity("��"+i, "ʱ��"+(2015+i), "��ַ"+(100+i)));
		}
		System.out.println(list.get(20).getBookname());
		ListView listview = (ListView) findViewById(R.id.borrow_list_lv);
		BorrowListBaseAdapter adapter = new BorrowListBaseAdapter(BorrowListActivity.this,list);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(new myOnItemClickListener() );
		
		
	}
	
	private class myOnItemClickListener implements AdapterView.OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			Intent intent = new Intent(BorrowListActivity.this,BorrowInfoActivity.class);
			startActivity(intent);
		}
		
	}
	
	

}

