package com.lvfq.code.mvp.demo3;

import com.lvfq.code.mvp.IHttpResultListener;

/**
 * LoginPresenter
 *
 * @author lvfq
 * @date 2017/7/13 下午11:21
 * @mainFunction :
 */

public class LoginPresenter3 extends BaseMvpPresenter<LoginView3> {

    private LoginModel3 loginModel;

    public LoginPresenter3() {
        loginModel = new LoginModel3();
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
                // 其他的数据操作。
                loginModel.saveUserData();

                if (getView() != null) {
                    // 最终返回界面，进行 UI 操作。
                    getView().onResult(t);
                }
            }
        });
    }
}
