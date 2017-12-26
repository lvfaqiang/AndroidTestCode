package com.lvfq.code.dagger_android;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * DaggerAndroidModule1
 *
 * @author FaQiang on 2017/12/24 上午12:43
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 */
@Module/*(subcomponents = {D_A_Subcomponent.class})*/
public abstract class DAModule1 {

    // 方式 ①

//    需要配置 Module 后面的 subcomponents
//    @Binds
//    @IntoMap
//    @ActivityKey(D_A_Activity1.class)
//    abstract AndroidInjector.Factory<? extends Activity>
//    bindActivityInjectorFactory(D_A_Subcomponent.Builder builder);


    // 方式 ②

    // 这种写法表明 DAActivyt1 和 当前 Module 绑定，
    @ContributesAndroidInjector
    abstract DAActivity1 contributeMainActivity();
}
