package com.lvfq.code.proxy.demo;

import android.app.Notification;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.RemoteViews;

import com.lvfq.code.R;

/**
 * NotifyNormal
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/12/6 下午11:39
 * @desc :
 */

public class NotifyBig extends Notify {
    public NotifyBig(Context context) {
        super(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void send() {
        Notification notification = builder.build();
        notification.contentView = new RemoteViews(context.getPackageName(), R.layout.layout_notify_normal_remoteview);
        notification.bigContentView = new RemoteViews(context.getPackageName(), R.layout.layout_notify_normal_big_remoteview);
        nm.notify(0, notification);
    }

    @Override
    public void cancel() {
        nm.cancel(0);
    }
}
