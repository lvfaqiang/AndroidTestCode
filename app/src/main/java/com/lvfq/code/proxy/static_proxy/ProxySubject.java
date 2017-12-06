package com.lvfq.code.proxy.static_proxy;

/**
 * ProxySubject
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/12/6 下午1:53
 * @desc :
 */

public class ProxySubject implements ISubject {

    private ISubject subject;

    public ProxySubject(ISubject subject) {
        this.subject = subject;
    }

    @Override
    public void start() {
        subject.start();
    }

    @Override
    public void pause() {
        subject.pause();
    }

    @Override
    public void stop() {
        subject.stop();
    }

    @Override
    public String result() {
        return subject.result();
    }
}
