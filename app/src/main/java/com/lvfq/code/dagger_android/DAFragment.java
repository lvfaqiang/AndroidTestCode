package com.lvfq.code.dagger_android;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.Fragment;

import dagger.android.support.AndroidSupportInjection;

/**
 * DAFragment
 *
 * @author FaQiang on 2018/1/2 下午12:32
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 */

public class DAFragment extends Fragment {
    @Override
    public void onAttach(Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            // Perform injection here before M, L (API 22) and below because onAttach(Context)
            // is not yet available at L.
            AndroidSupportInjection.inject(this);
        }
        super.onAttach(activity);
    }

    @Override
    public void onAttach(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Perform injection here for M (API 23) due to deprecation of onAttach(Activity).
            AndroidSupportInjection.inject(this);
        }
        super.onAttach(context);
    }

}
