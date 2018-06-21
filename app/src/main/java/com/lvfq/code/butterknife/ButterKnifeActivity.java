package com.lvfq.code.butterknife;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.lvfq.code.R;
import com.lvfq.code.butterknife.annotation.BindView;
import com.lvfq.code.butterknife.annotation.ButterKnife;
import com.lvfq.code.butterknife.annotation.OnClick;

/**
 * ButterKnifeActivity
 *
 * @author FaQiang on 2018/6/21 下午12:50
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 */
public class ButterKnifeActivity extends AppCompatActivity {

    @BindView(R.id.bk_tv_1)
    TextView tv1;

    @BindView(R.id.bk_tv_2)
    TextView tv2;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butter_knife);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.bk_btn)
    public void OnClick(View view) {
        tv1.setText("这是 TextView 1");
        tv2.setText("这是 TextView 2");
    }
}
