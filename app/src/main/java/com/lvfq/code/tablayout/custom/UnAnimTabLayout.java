package com.lvfq.code.tablayout.custom;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 重写TabLayout关于mSelectedTab的方法，解决动画卡断问题<br />
 * 利用反射将mSelectedTab设置为null， 这样可以做到禁用动画<br />
 * 利用静态代理重写tabSelect事件，解决mSelectedTab置为null后重复点击某一item会多次回调的问题
 */
public class UnAnimTabLayout extends TabLayout {

    private Tab mCurrentSelectedTab;

    public UnAnimTabLayout(Context context) {
        super(context);
    }

    public UnAnimTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UnAnimTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void removeAllTabs() {
        mCurrentSelectedTab = null;
        super.removeAllTabs();
    }

    public int getSelectedTabPosition() {
        return mCurrentSelectedTab != null ? mCurrentSelectedTab.getPosition() : -1;
    }

    /**
     * 重写父类的remove逻辑<br />
     * 具体实现方式： 1. 在调用真正remove之前将mSelectedTab设置为自己保存的mCurrentSelectedTab<br />
     * 2. 调用父类的removeTabAt()方法<br />
     * 3. 将父类的mSelectedTab置为null
     * @param position
     */
    public void removeTabAt(int position) {
        Field mSelectedTab = null;
        try {
            mSelectedTab = getClass().getSuperclass().getDeclaredField("mSelectedTab");
            mSelectedTab.setAccessible(true);
            mSelectedTab.set(this, mCurrentSelectedTab);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        super.removeTabAt(position);

        if (mSelectedTab != null && mSelectedTab.isAccessible()) {
            try {
                mSelectedTab.set(this, null);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public void addOnTabSelectedListener(@NonNull OnTabSelectedListener listener) {
        super.addOnTabSelectedListener(new TabSelectedListenerProxy(listener));
    }

    // proxy for tab event listener
    class TabSelectedListenerProxy implements OnTabSelectedListener {

        private OnTabSelectedListener mListener;
        TabSelectedListenerProxy(OnTabSelectedListener li) {
            mListener = li;
        }

        @Override
        public void onTabSelected(Tab tab) {
            final Tab currentTab = mCurrentSelectedTab;

            try {
                Field mSelectedTab = UnAnimTabLayout.class.getSuperclass().getDeclaredField("mSelectedTab");
                mSelectedTab.setAccessible(true);
                mSelectedTab.set(UnAnimTabLayout.this, null);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            if (currentTab == tab) {
                if (currentTab != null) {
                    try {
                        Method dispatchTabUnselected = UnAnimTabLayout.class.getSuperclass()
                                .getDeclaredMethod("dispatchTabReselected", Tab.class);
                        dispatchTabUnselected.setAccessible(true);
                        dispatchTabUnselected.invoke(UnAnimTabLayout.this, tab);

                        final int newPosition = tab != null ? tab.getPosition() : Tab.INVALID_POSITION;
                        Method setScrollPosition = UnAnimTabLayout.class.getSuperclass()
                                .getDeclaredMethod("setScrollPosition", int.class, float.class, boolean.class);
                        setScrollPosition.setAccessible(true);
                        setScrollPosition.invoke(UnAnimTabLayout.this, newPosition, 0f, true);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }

            if (currentTab != null && currentTab == tab) { return;}

            if (currentTab != null) {
                try {
                    Method dispatchTabUnselected = UnAnimTabLayout.class.getSuperclass()
                            .getDeclaredMethod("dispatchTabUnselected", Tab.class);
                    dispatchTabUnselected.setAccessible(true);
                    dispatchTabUnselected.invoke(UnAnimTabLayout.this, currentTab);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            mCurrentSelectedTab = tab;
            mListener.onTabSelected(tab);
        }

        @Override
        public void onTabUnselected(Tab tab) {
            // 永远不会执行到～
        }

        @Override
        public void onTabReselected(Tab tab) {
            // 永远不会执行到～
        }
    }
}
