package com.lvfq.code.proxy;

import com.lvfq.code.proxy.dynamic_proxy.DynamicProxy;
import com.lvfq.code.proxy.static_proxy.ISubject;
import com.lvfq.code.proxy.static_proxy.RealSubject;

import java.lang.reflect.Proxy;

/**
 * ProxyMain
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/12/6 下午1:46
 * @desc :
 */

public class ProxyMain {
    public static void main(String[] args) {
        // 测试静态代理
        RealSubject realSubject = new RealSubject();
//        ProxySubject proxySubject = new ProxySubject(realSubject);
//        proxySubject.start();
//        proxySubject.pause();
//        proxySubject.stop();
//        System.out.println("result " + proxySubject.result());

        // 测试动态代理
        DynamicProxy dynamicProxy = new DynamicProxy(realSubject);
        ISubject subject = (ISubject) Proxy.newProxyInstance(ISubject.class.getClassLoader(), new Class[]{ISubject.class}, dynamicProxy);

        subject.start();
        subject.pause();
        subject.stop();

        System.out.println("result " + subject.result());
    }
}
