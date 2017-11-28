package com.lvfq.code.designpatterns.builder;

/**
 * BuilderMain
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/11/27 下午11:51
 * @desc :
 */

public class BuilderMain {
    public static void main(String[] args) {
        MacBook1.MacBuilder builder = new MacBook1.MacBuilder().setBoard("board").setDisplay("display");
        System.out.println(builder.create().toString());
    }
}
