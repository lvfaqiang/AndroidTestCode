package com.lvfq.code.glide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

import com.lvfq.code.R;
import com.lvfq.library.utils.LvV;

/**
 * GlideActivity
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/9/2 下午3:17
 * @desc :
 */

public class GlideActivity extends FragmentActivity {

    ImageView iv_1;
    ImageView iv_2;
    ImageView iv_3;
    ImageView iv_4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);

        iv_1 = LvV.find(this, R.id.iv_1);
        iv_2 = LvV.find(this, R.id.iv_2);
        iv_3 = LvV.find(this, R.id.iv_3);
        iv_4 = LvV.find(this, R.id.iv_4);

//        RequestOptions options = new RequestOptions();
//        options.centerCrop();
//
//        Glide.with(this)
//                .load(Data.getImgs(1))
//                .apply(options)
//                .into(iv_1);

//        GlideApp.with(this)
//                .load(Data.getImgs(1))
//                .fitCenter()
//                .into(iv_1);

//        GlideApp.with(this).load(Data.getImgs(1)).into(iv_1);


    }
}
