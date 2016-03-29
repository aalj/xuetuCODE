package com.xuetu.fragment;

import com.xuetu.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class StudyTimeBillBoardFragment extends Fragment {
	ListView listview_billboard;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.listview_paihangbang, null);
		listview_billboard = (ListView) view.findViewById(R.id.listview_billboard);

		return view;
	}

}
