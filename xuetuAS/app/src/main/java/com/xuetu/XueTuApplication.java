package com.xuetu;

import android.app.Application;
import android.content.Context;

import com.xuetu.databases.DaoMaster;
import com.xuetu.databases.DaoSession;
import com.xuetu.entity.Coupon;
import com.xuetu.entity.Student;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class XueTuApplication extends Application {


    private static Context mContext;
    private Student student = null;
    private Set<Integer> set = new HashSet<Integer>();
    private Set<Integer> setQuesWithImg = new HashSet<Integer>();

    private static DaoMaster master = null;
    private static DaoSession session = null;



    public Set<Integer> getSetQuesWithImg() {
        return setQuesWithImg;
    }

    public void setSetQuesWithImg(Set<Integer> setQuesWithImg) {
        this.setQuesWithImg = setQuesWithImg;
    }

    public Set<Integer> getSet() {
        return set;
    }

    public void setSet(Set<Integer> set) {
        this.set = set;
    }

    private List<Coupon> listConpun = new ArrayList<Coupon>();

    public Student getStudent() {
        return student;
    }

    public List<Coupon> getListConpun() {
        return listConpun;
    }

    public void setListConpun(List<Coupon> listConpun) {
        this.listConpun = listConpun;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public void onCreate() {

        super.onCreate();
        mContext=this;
        student = new Student();
//        		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
//        	            .denyCacheImageMultipleSizesInMemory()
//        	            .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
//        	            .memoryCacheSize(2 * 1024 * 1024)
//        	            .discCacheSize(50 * 1024 * 1024)
//        	            .denyCacheImageMultipleSizesInMemory()
//        	            .discCacheFileNameGenerator(new Md5FileNameGenerator())
//        	            .tasksProcessingOrder(QueueProcessingType.LIFO)
//        	            .discCacheFileCount(100)
//        	            .writeDebugLogs()
//        	            .build();
//        	        ImageLoader.getInstance().init(config);
    }



    public static Context getAppContext() {
        return mContext;
    }

    public static DaoMaster getDaoMaster(Context context){
        if(master == null){
            DaoMaster.OpenHelper openHelper = new DaoMaster.DevOpenHelper(context,"xuetu",null);
            master = new DaoMaster(openHelper.getWritableDatabase());
        }
        return master;
    }

    public static DaoSession getDaoSession(Context context){
        if(session ==null){
            if(master==null){
                master=getDaoMaster(context);
            }
        }
        session = master.newSession( );
        return session;
    }
}