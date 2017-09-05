package com.lvfq.code.drawable;

import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lvfq.code.R;
import com.lvfq.library.utils.LvV;

/**
 * ClipDrawableActivity
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/9/5 下午1:34
 * @desc :
 */

public class ClipDrawableActivity extends AppCompatActivity {

    private TextView tv_start;
    private TextView tv_end;
    private ImageView iv_clip;
    private ClipDrawable clipDrawable;
    private static final int MAX = 100;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clip_drawable);

        tv_start = LvV.find(this, R.id.tv_start);
        tv_end = LvV.find(this, R.id.tv_end);
        iv_clip = LvV.find(this, R.id.iv_clip);

        clipDrawable = (ClipDrawable) iv_clip.getDrawable();
        clipDrawable.setLevel(0);

        tv_end.setText(MAX + "");


    }

    public void start(View view) {
        new Thread() {
            Handler handler = new Handler(getMainLooper()) {
                @Override
                public void handleMessage(Message msg) {
                    if (msg.what == 1) {
                        int level = clipDrawable.getLevel();
                        int pro = (int) ((level / 10000d) * MAX);
                        tv_start.setText(pro + "");
                        if (pro < MAX) {
                            clipDrawable.setLevel(clipDrawable.getLevel() + 200);
                        } else {
                            Toast.makeText(ClipDrawableActivity.this, "加载完成", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            };

            @Override
            public void run() {

                while (clipDrawable.getLevel() < 10000) {
                    try {
                        sleep(300);
                        handler.sendEmptyMessage(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }.start();
    }


}
