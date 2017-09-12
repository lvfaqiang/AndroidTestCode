package com.lvfq.code.service;

import android.app.ActivityManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lvfq.code.R;
import com.lvfq.library.utils.LvLog;

import java.util.List;

/**
 * ServiceActivity
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/9/11 下午3:09
 * @desc :
 */

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener {

    private MyBindService.DownloadBinder downloadBinder;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (MyBindService.DownloadBinder) service;
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            LvLog.i("Service Activity onServiceDisconnected : " + name);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        findViewById(R.id.btn_start_service).setOnClickListener(this);
        findViewById(R.id.btn_end_service).setOnClickListener(this);
        // 绑定服务
        findViewById(R.id.btn_bind_service).setOnClickListener(this);
        findViewById(R.id.btn_unbind_service).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start_service:
                // TODO: 2017/9/11 启动服务
                Intent startIntent = new Intent(this, MyService.class);
                startService(startIntent);
                break;
            case R.id.btn_end_service:
                // TODO: 2017/9/11 停止服务
                Intent stopIntent = new Intent(this, MyService.class);
                stopService(stopIntent);
                break;

            case R.id.btn_bind_service:
                // TODO: 2017/9/11 绑定服务
                Intent bindIntent = new Intent(this, MyBindService.class);
                bindService(bindIntent, connection, Service.BIND_AUTO_CREATE);
                break;
            case R.id.btn_unbind_service:
                // TODO: 2017/9/11 解绑服务
                boolean isRunning = isServiceRunning(this, MyBindService.class.getName());
                LvLog.i("isRunning : " + isRunning);

                if (isRunning) {
                    unbindService(connection);
                }
                break;
        }
    }

    public static boolean isServiceRunning(Context context, String serviceName) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> runningServiceInfos = am.getRunningServices(200);
        if (runningServiceInfos.size() <= 0) {
            return false;
        }
        for (ActivityManager.RunningServiceInfo serviceInfo : runningServiceInfos) {
            if (serviceInfo.service.getClassName().equals(serviceName)) {
                return true;
            }
        }
        return false;
    }
}
