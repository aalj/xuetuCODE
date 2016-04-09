package com.xuetu.entity;

import java.text.DecimalFormat;

public class PaiXu {
	public int[] paixu(int [] today_studytime)
	{
		for(int i=0;i<today_studytime.length-1;i++)
		{
			for(int j=i+1;j<today_studytime.length;j++)
			{
				if(today_studytime[i]>today_studytime[j])
				{
					int temp=today_studytime[i];
					today_studytime[i]=today_studytime[j];
					today_studytime[j]=temp;
				}
			}
		}
		return today_studytime;
	}
	
	public String getPercent(int x,int total){  
		   String result="";//接受百分比的值  
//		   double x_double=x*1.0;  
		   double tempresult=1.00*x/total;  
		   System.out.println( x +"```````````"+total);
		   //NumberFormat nf   =   NumberFormat.getPercentInstance();     注释掉的也是一种方法  
		   //nf.setMinimumFractionDigits( 2 );        保留到小数点后几位  
		   DecimalFormat df1 = new DecimalFormat("0%");    
		   //result=nf.format(tempresult);     
		   result= df1.format(tempresult);    
		   return result;  
		}  
	
}
