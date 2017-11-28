package com.lvfq.code.designpatterns.factory;

/**
 * FactoryClient
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/11/28 下午12:58
 * @desc :
 */

public class FactoryClient {
    public static void main(String[] args) {
        Factory factory = new ConcreteFactory();
        // 直接创建，
        factory.createProduct().method();
        // 利用反射方式创建
        factory.createProduct(ConcreteProductA.class).method();
    }
}
