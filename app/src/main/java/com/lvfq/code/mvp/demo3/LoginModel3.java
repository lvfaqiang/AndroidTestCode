package com.lvfq.code.mvp.demo3;

import com.lvfq.code.mvp.Http;
import com.lvfq.code.mvp.IHttpResultListener;

/**
 * LoginModel
 *
 * @author lvfq
 * @date 2017/7/13 下午11:22
 * @mainFunction : M  层
 */

public class LoginModel3 {

    public void login(String userName, String passWord, IHttpResultListener listener) {
        Http.http_login(userName, passWord, listener);
    }

    /**
     * 保存登录用户数据
     */
    public void saveUserData(){
        //...
    }

}
