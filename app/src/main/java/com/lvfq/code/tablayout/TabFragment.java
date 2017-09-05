package com.lvfq.code.tablayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * TabFragment
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/9/1 下午4:38
 * @desc :
 */

public class TabFragment extends Fragment {


    public static TabFragment getInstance(String string) {
        TabFragment instance = new TabFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", string);
        instance.setArguments(bundle);
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        String text = getArguments().getString("title");
        TextView textView = new TextView(getActivity());
        textView.setText(text);
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        textView.setGravity(Gravity.CENTER);
        Log.i("lfq", "title : " + text + " , hashCode : " + hashCode());
        return textView;
    }
}
