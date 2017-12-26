package com.lvfq.code.dagger_android;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * DaggerAndroidSubcomponent
 *
 * @author FaQiang on 2017/12/24 上午12:40
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 */

@Subcomponent
public interface DASubcomponent extends AndroidInjector<DAActivity1> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<DAActivity1> {
    }
}
