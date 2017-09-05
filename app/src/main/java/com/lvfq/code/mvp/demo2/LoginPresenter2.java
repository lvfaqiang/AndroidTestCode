package com.lvfq.code.mvp.demo2;

import com.lvfq.code.mvp.IHttpResultListener;

/**
 * LoginPresenter
 *
 * @author lvfq
 * @date 2017/7/13 下午11:21
 * @mainFunction :
 */

public class LoginPresenter2 {

    private LoginModel2 loginModel;
    private LoginView2 loginView;

    public LoginPresenter2() {
        loginModel = new LoginModel2();
    }

    public void attachView(LoginView2 loginView) {
        this.loginView = loginView;
    }

    public void dettachView() {
        this.loginView = null;
    }

    /**
     * 登录
     *
     * @param userName
     * @param passWord
     */
    public void login(String userName, String passWord) {
        loginModel.login(userName, passWord, new IHttpResultListener() {
            @Override
            public void onResult(String t) {
                if (loginView != null) {
                    loginView.onResult(t);
                }
            }
        });
    }
}
