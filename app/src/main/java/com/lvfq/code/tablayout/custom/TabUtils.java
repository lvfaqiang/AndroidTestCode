package com.lvfq.code.tablayout.custom;

import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.design.widget.TabLayout;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Field;

/**
 * 修改Tablayout tab的字体和shadow
 * created by qibin
 */
public class TabUtils {

    public static void boldTab(TabLayout.Tab tab) {
        setTabStyle(tab, Typeface.DEFAULT_BOLD, 0, 0, 0, 0);
    }

    /**
     * 选中某条， 改变样式
     *
     * @param tabLayout
     * @param currentTab
     */
    public static void tabSelect(TabLayout tabLayout, TabLayout.Tab currentTab) {
        int tabCount = tabLayout.getTabCount();
        TabLayout.Tab tab;
        for (int i = 0; i < tabCount; i++) {
            tab = tabLayout.getTabAt(i);
            setTabStyle(tab, Typeface.DEFAULT, 0, 0, 0, 0x00000000);
        }

        setTabStyle(currentTab, Typeface.DEFAULT_BOLD, 1, 2, 2, 0x55000000);
    }

    public static void tabSelectAt(TabLayout tabLayout, TabLayout.Tab currentTab, int position) {
        int tabCount = tabLayout.getTabCount();
        TabLayout.Tab tab;
        for (int i = 0; i < tabCount; i++) {
            tab = tabLayout.getTabAt(i);
            if (i != position) {
                setTabStyle(tab, Typeface.DEFAULT, 0, 0, 0, 0x00000000);
            } else {
                setTabStyle(currentTab, Typeface.DEFAULT_BOLD, 1, 2, 2, 0x55000000);
            }
        }
    }

    /**
     * 通过反射去设置样式
     *
     * @param tab
     * @param tf
     * @param radius
     * @param dx
     * @param dy
     * @param color
     */
    public static void setTabStyle(TabLayout.Tab tab, Typeface tf, int radius, float dx, float dy, int color) {
        TextView tv = getTextView(tab);
        if (tv == null) {
            return;
        }
        //TODO 暂时不做阴影效果
//        tv.setTextSize(18);
//        tv.setTypeface(tf);
//        tv.setShadowLayer(radius, dx, dy, color);
    }

    private static TextView getTextView(TabLayout.Tab tab) {
        try {
            Field mView = tab.getClass().getDeclaredField("mView");
            mView.setAccessible(true);
            Object mViewObj = mView.get(tab);
            Field mTextView = mViewObj.getClass().getDeclaredField("mTextView");
            mTextView.setAccessible(true);
            return (TextView) mTextView.get(mViewObj);
        } catch (Exception e) {

        }

        return null;
    }

    /**
     * 通过反射去设置下划线样式bufen
     *
     * @param tab
     * @param leftDip
     * @param rightDip
     */
    public static void setIndicator(TabLayout tab, int leftDip, int rightDip) {
        try {
            Field tabStrip = TabLayout.class.getDeclaredField("mTabStrip");
            tabStrip.setAccessible(true);
            LinearLayout llTab = (LinearLayout) tabStrip.get(tab);
            int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
            int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

            for (int i = 0; i < llTab.getChildCount(); i++) {
                View child = llTab.getChildAt(i);
                child.setPadding(0, 0, 0, 0);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
                params.leftMargin = left;
                params.rightMargin = right;
                child.setLayoutParams(params);
                child.invalidate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 加粗current
     *
     * @param tabLayout
     * @param currentTab
     */
    public static void tabBoldCurrent(TabLayout tabLayout, TabLayout.Tab currentTab) {
        int tabCount = tabLayout.getTabCount();
        TabLayout.Tab tab;
        for (int i = 0; i < tabCount; i++) {
            tab = tabLayout.getTabAt(i);
            TextView tv = getTextView(tab);
            if (tv == null) {
                continue;
            }
            tv.setTypeface(Typeface.DEFAULT);
        }

        TextView tv = getTextView(currentTab);
        if (tv == null) {
            return;
        }
        tv.setTypeface(Typeface.DEFAULT_BOLD);
    }


    public static void enableTabs(TabLayout tabLayout, Boolean enable) {
        ViewGroup viewGroup = getTabViewGroup(tabLayout);
        if (viewGroup != null)
            for (int childIndex = 0; childIndex < viewGroup.getChildCount(); childIndex++) {
                View tabView = viewGroup.getChildAt(childIndex);
                if (tabView != null)
                    tabView.setEnabled(enable);
            }
    }

    public static View getTabView(TabLayout tabLayout, int position) {
        View tabView = null;
        ViewGroup viewGroup = getTabViewGroup(tabLayout);
        if (viewGroup != null && viewGroup.getChildCount() > position)
            tabView = viewGroup.getChildAt(position);

        return tabView;
    }

    private static ViewGroup getTabViewGroup(TabLayout tabLayout) {
        ViewGroup viewGroup = null;

        if (tabLayout != null && tabLayout.getChildCount() > 0) {
            View view = tabLayout.getChildAt(0);
            if (view != null && view instanceof ViewGroup)
                viewGroup = (ViewGroup) view;
        }
        return viewGroup;
    }
}
