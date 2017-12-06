package com.lvfq.code.proxy.static_proxy;

/**
 * ISubject
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/12/6 下午1:47
 * @desc :
 */

public interface ISubject {
    void start();

    void pause();

    void stop();

    String result();
}
