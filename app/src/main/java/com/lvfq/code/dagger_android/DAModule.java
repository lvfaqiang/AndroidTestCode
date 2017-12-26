package com.lvfq.code.dagger_android;

import dagger.Module;
import dagger.Provides;

/**
 * DAModule
 *
 * @author FaQiang on 2017/12/26 上午12:50
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 */

@Module(includes = {DAModule1.class})
public class DAModule {

    @Provides
    public String getString() {
        return "this is D_A_Module1";
    }

}
