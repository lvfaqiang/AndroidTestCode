package com.lvfq.code.proxy.demo;

import android.content.Context;
import android.os.Build;

/**
 * NotifyProxy
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/12/6 下午11:55
 * @desc :
 */

public class NotifyProxy extends Notify {

    private Notify notify;

    /**
     * 这里就相当于使用了代理类，来根据情况判断使用哪一种通知。
     *
     * @param context
     */
    public NotifyProxy(Context context) {
        super(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            notify = new NotifyHeadsUp(context);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            notify = new NotifyBig(context);
        } else {
            notify = new NotifyNormal(context);
        }
    }

    @Override
    public void send() {
        notify.send();
    }

    @Override
    public void cancel() {
        notify.cancel();
    }
}
