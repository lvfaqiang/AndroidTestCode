package com.lvfq.code.dagger2.demo;

import com.lvfq.code.dagger2.HttpComponent;
import com.lvfq.code.dagger2.annotation.ActivityScope;

/**
 * Component2
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/12/12 下午11:06
 * @desc :
 */
@ActivityScope
@dagger.Component(dependencies = HttpComponent.class)
public interface Component2 {
    void init(Dagger2Activity2 activity2);
}
