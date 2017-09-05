package com.lvfq.code.mvp.demo3;

/**
 * BaseMvpPresenter
 *
 * @author lvfq
 * @date 2017/7/17 下午3:25
 * @mainFunction :
 */

public class BaseMvpPresenter<V extends IBaseMvpView> implements IBasePresenter<V> {
    private V view;

    @Override
    public V getView() {
        return view;
    }

    @Override
    public void attachView(V view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }
}
