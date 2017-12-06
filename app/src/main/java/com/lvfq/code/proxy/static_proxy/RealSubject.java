package com.lvfq.code.proxy.static_proxy;

/**
 * RealSubject
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/12/6 下午1:48
 * @desc :
 */

public class RealSubject implements ISubject {
    @Override
    public void start() {
        System.out.println("this is real subject start()");
    }

    @Override
    public void pause() {
        System.out.println("this is real subject pause()");
    }

    @Override
    public void stop() {
        System.out.println("this is real subject stop()");
    }

    @Override
    public String result() {
        return "this is real subject result String ";
    }
}
