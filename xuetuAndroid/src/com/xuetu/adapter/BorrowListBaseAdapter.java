/**
 * BorrowListBaseAdapter.java
 * com.librarybooksearch.baseAdapter
 *
 * Function�� TODO 
 *
 *   ver     date      		author
 * ��������������������������������������������������������������������
 *   		 2015��11��6�� 		view
 *
 * Copyright (c) 2015, TNT All Rights Reserved.
*/

package com.xuetu.adapter;

import java.util.List;

import com.xuetu.R;
import com.xuetu.entity.BorrowInfoEntity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * ClassName:BorrowListBaseAdapter Function: TODO ADD FUNCTION Reason: TODO ADD
 * REASON
 *
 * @author view
 * @version
 * @since Ver 1.1
 * @Date 2015��11��6�� ����8:53:34
 *
 * @see
 * 
 */
public class BorrowListBaseAdapter extends BaseAdapter {

	private List<BorrowInfoEntity> list = null;
	private LayoutInflater inflater = null;
	private TextView borrow_name_te= null;
	private TextView borrow_time_te= null;
	private TextView borrow_loca_te= null;

	public BorrowListBaseAdapter(Context context, List<BorrowInfoEntity> list) {
		this.list = list;
		inflater = LayoutInflater.from(context);

	}

	@Override
	public int getCount() {

		// TODO Auto-generated method stub
		return list.size();

	}

	@Override
	public Object getItem(int position) {

		// TODO Auto-generated method stub
		return list.get(position);

	}

	@Override
	public long getItemId(int position) {

		// TODO Auto-generated method stub
		return position;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = null;
		if (view == null) {
			view = inflater.inflate(R.layout.borrow_item, null);
		} else {
			view = convertView;

		}
		
			borrow_loca_te = (TextView) view.findViewById(R.id.borrow_loca_te);
			borrow_name_te = (TextView) view.findViewById(R.id.borrow_name_te);
			borrow_time_te = (TextView) view.findViewById(R.id.borrow_time_te);
		
		borrow_name_te .setText(list.get(position).getBookname()); 
		borrow_loca_te .setText(list.get(position).getBookloca());
		borrow_time_te.setText(list.get(position).getBooktime());
		return view;

	}

}
