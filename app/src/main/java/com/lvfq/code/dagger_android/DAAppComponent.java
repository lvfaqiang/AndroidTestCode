package com.lvfq.code.dagger_android;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;

/**
 * DaggerAndroidAppComponent
 *
 * @author FaQiang on 2017/12/24 上午1:24
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 * 这个 Component 是绑定 {@link DaggerAndroidApp }Application 级别的，标注 Singleton 单例
 * Component 配置的 Module 中，必须要包含 AndroidInjectionModule.class ,可以是在其他 Module 中，也可以直接 在此处添加。
 * 我这里是在 {@link DAGlobalSingleModule} 中定义单例, {@link DAModule} 为注入的 DAActivity1 实例，
 * 这里可以先去大致的了解一下 Dagger.android, 然后再回来看代码
 * <p>
 * 就目前所了解的程度而言，这个 Component 暂时就这么写，
 */

@Singleton
@Component(modules = {DAGlobalSingleModule.class, DAModule.class})
public interface DAAppComponent extends AndroidInjector<DaggerAndroidApp> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<DaggerAndroidApp> {
    }

}
