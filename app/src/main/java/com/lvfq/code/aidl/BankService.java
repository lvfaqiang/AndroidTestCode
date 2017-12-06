package com.lvfq.code.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * BankService
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/12/6 下午4:45
 * @desc :
 */

public class BankService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new BankBinder();
    }
}
