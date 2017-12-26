package com.lvfq.code.dagger_android;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * DAGlobalSingleModule
 *
 * @author FaQiang on 2017/12/26 下午10:39
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 * 这个主要是为了演示定义全局的 静态变量，
 * {@link DAAppModule} 如果没有或者不需要定义全局变量，这里可以直接在 引入 {@link DAGlobalSingleModule }的地方 替换为 {@link DAAppModule}
 * 当然，即使不用，或者暂时没定义全局变量，个人觉得这个还是要保留，所以我感觉最好刚开始就把这两层定义出来，
 * 因为 {@link DAAppModule} 是一个抽象类，抽象类中如果使用 @Provides 注解某实现方法，必须使用 static 修饰该方法。
 */

@Module(includes = {DAAppModule.class})
public class DAGlobalSingleModule {

    /**
     * 这里定义全局的单例
     *
     * @return
     */
    @Singleton
    @Provides
    public OkHttpClient getOkHttp() {
        return new OkHttpClient.Builder().build();
    }

}
