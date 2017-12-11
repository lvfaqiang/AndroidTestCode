package com.lvfq.code.dagger2;

/**
 * Component
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/12/8 下午12:38
 * @desc :
 */

@dagger.Component(modules = {UserModule.class}, dependencies = {HttpComponent.class})
public interface Component {
    // 这里对 Module 和 Activity 进行关联
    void init(Dagger2Activity activity);
}
