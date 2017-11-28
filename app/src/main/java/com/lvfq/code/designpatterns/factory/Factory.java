package com.lvfq.code.designpatterns.factory;

/**
 * Factory
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/11/28 下午12:57
 * @desc :
 */

public abstract class Factory {
    public abstract Product createProduct();

    /**
     * 利用反射来创建对象
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public abstract <T extends Product> T createProduct(Class<T> clazz);
}
