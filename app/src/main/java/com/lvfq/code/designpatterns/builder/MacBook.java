package com.lvfq.code.designpatterns.builder;

/**
 * MacBook
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/11/27 下午11:29
 * @desc :
 */

public class MacBook extends Computer {
    MacBook() {
    }

    @Override
    public void setOs() {
        mOS = "Mac OS HighSierra 10.13.1";
    }
}
