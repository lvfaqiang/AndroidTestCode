package com.lvfq.code.designpatterns.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * Coder
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/11/29 下午11:37
 * @desc : 观察者
 */

public class Coder implements Observer {
    private String name;

    public Coder(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println(" Hi ," + name + " , 更新内容了。" + arg);
    }
}
