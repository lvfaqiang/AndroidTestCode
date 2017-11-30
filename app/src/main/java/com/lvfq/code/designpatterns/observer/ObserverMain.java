package com.lvfq.code.designpatterns.observer;

/**
 * ObserverMain
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/11/29 下午11:39
 * @desc :
 */

public class ObserverMain {
    public static void main(String[] args) {

        // 四个观察者 Observer
        Coder coder1 = new Coder("一");
        Coder coder2 = new Coder("二");
        Coder coder3 = new Coder("三");
        Coder coder4 = new Coder("四");

        // 被观察者，Observable
        DevTech devTech = new DevTech();
        devTech.addObserver(coder1);
        devTech.addObserver(coder2);
        devTech.addObserver(coder3);
        devTech.addObserver(coder4);
        devTech.postMessage("测试内容");
    }
}
