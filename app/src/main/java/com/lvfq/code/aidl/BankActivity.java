package com.lvfq.code.aidl;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.lvfq.code.IBankAIDL;
import com.lvfq.code.R;

/**
 * BankActivity
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/12/6 下午4:46
 * @desc :
 */

public class BankActivity extends AppCompatActivity implements View.OnClickListener {

    private IBankAIDL bankBinder;
    //    private BankBinder bankBinder;
    private TextView tv_msg;

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
//            bankBinder = (BankBinder) service;
            bankBinder = IBankAIDL.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank);
        bindService(new Intent(this, BankService.class), conn, BIND_AUTO_CREATE);

        tv_msg = findViewById(R.id.tv_msg);
        findViewById(R.id.btn_create).setOnClickListener(this);
        findViewById(R.id.btn_save).setOnClickListener(this);
        findViewById(R.id.btn_take).setOnClickListener(this);
        findViewById(R.id.btn_destroy).setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_create:
                try {
                    tv_msg.setText(bankBinder.openAccount("发强", "123456"));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_save:
                try {
                    tv_msg.setText(bankBinder.saveMoney(10000, "发强"));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_take:
                try {
                    tv_msg.setText(bankBinder.takeMoney(5000, "发强", "123456"));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_destroy:
                try {
                    tv_msg.setText(bankBinder.destroyAccount("发强", "123456"));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
