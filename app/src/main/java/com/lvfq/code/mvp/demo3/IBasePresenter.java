package com.lvfq.code.mvp.demo3;

/**
 * IBasePresenter
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/7/26 下午12:40
 * @desc :
 */

public interface IBasePresenter<V extends IBaseMvpView> {
    V getView();

    void attachView(V view);

    void detachView();
}
