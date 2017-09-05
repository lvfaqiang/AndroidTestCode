package com.lvfq.code.mvp.demo1;

import com.lvfq.code.mvp.IHttpResultListener;

/**
 * LoginPresenter
 *
 * @author lvfq
 * @date 2017/7/13 下午11:21
 * @mainFunction :
 */

public class LoginPresenter {

    private LoginModel loginModel;
    private LoginView loginView;

    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
        loginModel = new LoginModel();
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
