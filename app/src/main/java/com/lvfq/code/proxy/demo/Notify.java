package com.lvfq.code.proxy.demo;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.lvfq.code.notification.NotificationTestActivity;

/**
 * Notify
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/12/6 下午11:22
 * @desc :
 */

public abstract class Notify {
    protected Context context;
    protected NotificationManager nm;
    protected NotificationCompat.Builder builder;

    public Notify(Context context) {
        this.context = context;

        nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(context.getApplicationInfo().icon)
                .setContentIntent(PendingIntent.getActivity(context, 0, new Intent(context, NotificationTestActivity.class), PendingIntent.FLAG_ONE_SHOT));

    }

    /**
     * 发送一条通知
     */
    public abstract void send();

    /**
     * 取消一条通知
     */
    public abstract void cancel();
}
