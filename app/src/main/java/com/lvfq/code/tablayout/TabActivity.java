package com.lvfq.code.tablayout;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.lvfq.library.utils.LvV;
import com.lvfq.myworkingtest.R;

/**
 * TabActivity
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/9/1 下午4:22
 * @desc :
 */

public class TabActivity extends FragmentActivity {

    private PagerSlidingTabStrip tabs;
    private ViewPager viewPager;

    private String[] titles = {"热点", "推荐", "标题党", "视频", "本地农业", "科技", "政治", "农业", "其他"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        tabs = LvV.find(this, R.id.tabs);
        viewPager = LvV.find(this, R.id.viewPager);

        TabAdapter adapter = new TabAdapter(getSupportFragmentManager());
        if (adapter != null) {
            viewPager.setAdapter(adapter);
            tabs.setViewPager(viewPager);
        }

    }

    class TabAdapter extends FragmentStatePagerAdapter {

        public TabAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public Fragment getItem(int pos) {
            return TabFragment.getInstance(titles[pos]);
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }

}
