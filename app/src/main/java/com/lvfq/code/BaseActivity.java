package com.lvfq.code;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.lvfq.code.mvp.demo3.BaseMvpPresenter;
import com.lvfq.code.mvp.demo3.IBaseMvpView;

/**
 * BaseActivity
 *
 * @author lvfq
 * @date 2017/7/17 下午3:04
 * @mainFunction :
 */

public abstract class BaseActivity<V extends IBaseMvpView, P extends BaseMvpPresenter<V>> extends FragmentActivity {

    private P presenter;

    public abstract P bindPresenter();

    public P getPresenter() {
        return presenter;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (presenter == null) {
            presenter = bindPresenter();
            if (presenter != null && this instanceof IBaseMvpView) {
                presenter.attachView((V) this);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
        }
    }
}
