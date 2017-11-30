package com.lvfq.code.designpatterns.observer;

import java.util.Observable;

/**
 * DevTech
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/11/29 下午11:38
 * @desc :
 */

public class DevTech extends Observable {

    public void postMessage(String content) {
        setChanged();
        notifyObservers(content);
    }


}
