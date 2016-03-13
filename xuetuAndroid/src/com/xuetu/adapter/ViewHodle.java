package com.xuetu.adapter;

import com.gc.materialdesign.views.Switch;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by stone on 16-3-8.
 */
public class ViewHodle {


    View myConverView = null;
    SparseArray<View> sparseArray = null;

    //参数一、表示上下文，用于引用布局到LIstVIew上
    //参数二、
    private ViewHodle(Context context, ViewGroup parent, int resoureId) {
        sparseArray = new SparseArray();
        LayoutInflater inflater = LayoutInflater.from(context);
        myConverView = inflater.inflate(resoureId, parent, false);
        myConverView.setTag(this);

    }

    public static ViewHodle getViewHodle(Context context, ViewGroup parnet, int reaourId, View converView) {
        //这里如果是null则表示当前并没有复用view
        if (converView == null) {
            return new ViewHodle(context, parnet, reaourId);
        }
        //如果执行当这俩表示当前的converVIew是复用
        return (ViewHodle) converView.getTag();

    }


    public View getConvertView() {

        return myConverView;
    }



    //通过ViewHodle得到单个空间对象
    public <T extends View> T getView(int resId) {
        //通过控件的ID 得到集合里面的 控件对象
        View view = sparseArray.get(resId);
        if (view == null) {
            //当通过控件在集合里面得不到控件的时候，通过viewfindVIewById得到控件对象
            view = myConverView.findViewById(resId);
            sparseArray.put(resId, view);
        }

        return (T) view;
    }


    public ViewHodle setText(int resID, String info) {
        TextView view = (TextView) getView(resID);
//        Log.i("TAG", "setText: \t" +view);
        view.setText(info);
        return this;
    }


    public ViewHodle setIma(int resID,int drawableID){
        ImageView ima = (ImageView)getView(resID);
        ima.setImageResource(drawableID);

        return this;

    }
    
    
    public ViewHodle setClick(int resID,boolean arg){
    	Switch  myswitch=(Switch)getView(resID);
    	myswitch.setChecked(arg);
    	
    	return this;
    	
    }



}