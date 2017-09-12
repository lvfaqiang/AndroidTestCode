package com.lvfq.code.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.lvfq.library.utils.LvLog;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // TODO: 2017/9/11  创建时调用
        LvLog.i("MyService onCreate...");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO: 2017/9/11 启动时调用
        LvLog.i("MyService onStartCommand...");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // TODO: 2017/9/11 销毁时调用
        LvLog.i("MyService onDestroy...");
    }
}
