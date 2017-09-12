package com.lvfq.code.alarm;

import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.Toast;

import java.util.Date;

/**
 * AlarmActivity
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/9/12 下午2:26
 * @desc :
 */

public class AlarmActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: 2017/9/12 测试没有达到预期效果。
        Intent intent = new Intent(this, AlarmService.class);
        startService(intent);

//        Toast.makeText(this, "服务已启动", Toast.LENGTH_SHORT).show();
    }

    /**
     * 连按两次退出应用
     **/
    long preTime;
    public static final long TWO_SECOND = 2 * 1000;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            long currentTime = new Date().getTime();

            // 如果时间间隔大于2秒, 不处理
            if ((currentTime - preTime) > TWO_SECOND) {
                // 显示消息
                Toast.makeText(this, "再按一次退出应用", Toast.LENGTH_SHORT).show();
                // 更新时间
                preTime = currentTime;
                // 截获事件,不再处理
                return true;
            }
            finish();
            android.os.Process.killProcess(Process.myPid());
            System.exit(0);

        }
        return super.onKeyDown(keyCode, event);
    }


}
