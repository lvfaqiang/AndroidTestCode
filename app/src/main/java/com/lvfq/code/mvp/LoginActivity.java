package com.lvfq.code.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.lvfq.code.BaseActivity;
import com.lvfq.code.ModelBean;
import com.lvfq.code.R;
import com.lvfq.code.http.HttpClient;
import com.lvfq.code.mvp.demo3.LoginPresenter3;
import com.lvfq.code.mvp.demo3.LoginView3;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * LoginActivity
 *
 * @author lvfq
 * @date 2017/7/12 下午9:46
 * @mainFunction :
 */

public class LoginActivity extends BaseActivity<LoginView3, LoginPresenter3> implements LoginView3 {

    @Override
    public LoginPresenter3 bindPresenter() {
        return new LoginPresenter3();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


    public void clickLogin(View view) {
//        getPresenter().login("发强", "123456");
        HttpClient.getService().serverInfo().enqueue(new Callback<ModelBean>() {
            @Override
            public void onResponse(Call<ModelBean> call, Response<ModelBean> response) {
                ModelBean bean = response.body();
                Log.i("lfq", bean.getResultData().isMainServer() + "");
            }

            @Override
            public void onFailure(Call<ModelBean> call, Throwable t) {
                Log.i("lfq", t.getMessage() + "");
            }
        });

//        Http.http_login("发强", "123456", this);
//        HttpClient.getService().login("lvfaqiang", "123456").enqueue(new Callback<ModelBean>() {
//            @Override
//            public void onResponse(Call<ModelBean> call, Response<ModelBean> response) {
//                ModelBean bean = response.body();
//                Log.i("lfq", "" + bean.getStatus());
//            }
//
//            @Override
//            public void onFailure(Call<ModelBean> call, Throwable t) {
//                Log.i("lfq", t.toString());
//            }
//        });
    }

    @Override
    public void onResult(final String s) {
        Toast.makeText(LoginActivity.this, s, Toast.LENGTH_SHORT).show();
        Log.i("lfq", "login Result: " + s);
    }
}
