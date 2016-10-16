package com.xuetu.ui.activity.user.api;

import com.xuetu.utils.StringUtlis;

/**
 * Created by liang on 2016/10/5.
 * 与用户相关的所有网络请求通过该类进行管理
 */

public class UsrModelManager {

    private static UsrModelManager usrModelManager = new UsrModelManager();
    private UserLoginApi api=null;

    /**
     * 采用单利的模式获取管理者对象
     * @return
     */
    public static UsrModelManager getInstance(){
        return usrModelManager;
    }

    /**
     * 构造方法，用于创建访问网络的api
     */
    public UsrModelManager() {
        api=new UserLoginApi();
    }

    public  void userLogin(String tel,String pwd){
        if(StringUtlis.isEmpty(tel)&&StringUtlis.isEmpty(pwd)){
            return;
        }
        api.userLogin(tel,pwd);
    }





}
