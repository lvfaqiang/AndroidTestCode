package com.lvfq.code.tablayout.custom;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.lvfq.code.R;
import com.lvfq.code.tablayout.TabFragment;
import com.lvfq.library.utils.LvV;

/**
 * TabActivity1
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/9/4 下午7:02
 * @desc :
 */

public class TabActivity1 extends AppCompatActivity {

    UnAnimTabLayout tabLayout;
    private ViewPager viewPager;
    private TabAdapter mAdapter;
    private String[] titles = {"热点", "推荐", "标题党", "视频", "本地农业", "科技", "政治", "农业", "其他"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab1);
        tabLayout = LvV.find(this, R.id.tab);
        viewPager = LvV.find(this, R.id.viewPager);
        mAdapter = new TabAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabSelectAdapter() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//                TabUtils.tabSelect(tabLayout, tab);
                TabUtils.tabBoldCurrent(tabLayout, tab);
//                int tag = (int) tab.getTag();
//                LvToastUtil.showToast(TabActivity1.this, tag + "");

            }
        });
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                TabUtils.setIndicator(tabLayout, 10, 10);
            }
        });
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
