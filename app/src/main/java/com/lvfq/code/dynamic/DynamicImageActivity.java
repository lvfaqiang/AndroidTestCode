package com.lvfq.code.dynamic;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lvfq.code.R;
import com.lvfq.code.dynamic.view.MultiImageView;
import com.lvfq.library.utils.LvToastUtil;
import com.lvfq.library.utils.LvV;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * DynamicImageActivity
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/8/25 上午9:16
 * @desc :
 */

public class DynamicImageActivity extends FragmentActivity {

    RecyclerView recyclerView;
    CommonAdapter<String> mAdapter;
    List<String> mList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_image);

        recyclerView = LvV.find(this, R.id.recyclerView);
        mList = Data.getImgs(9);

        initAdapter();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);

    }

    private void initAdapter() {
        mAdapter = new CommonAdapter<String>(this, R.layout.item_dynamic_image, mList) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                MultiImageView multiImage = holder.getView(R.id.multi_image);
                holder.setText(R.id.tv_title, (position + 1) + "张图");
                multiImage.setList(Data.getImgs(position + 1));
                final int pos = position;
                multiImage.setOnItemClickListener(new MultiImageView.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        LvToastUtil.showToast((Activity) mContext, "position1:" + pos + " , position2:" + position);
                    }
                });
            }
        };

    }
}
