package com.lvfq.code.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.os.SystemClock;

import com.lvfq.library.utils.LvLog;

public class AlarmService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LvLog.i("服务已启动。。。");
        new Thread(new Runnable() {
            @Override
            public void run() {
                LvLog.i("我还在。。。");
            }
        }).start();
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int hour = 10 * 1000;
        long mill = SystemClock.elapsedRealtime() + hour;

        Intent in = new Intent(this, AlarmService.class);
        PendingIntent pi = PendingIntent.getService(this, 0, in, 0);
        manager.cancel(pi);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            manager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, mill, pi);
        } else {
            manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, mill, pi);
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LvLog.i("服务已销毁。。。");
    }
}
