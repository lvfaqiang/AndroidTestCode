package com.lvfq.code.designpatterns.builder;

/**
 * Director
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/11/27 下午11:37
 * @desc :
 */

public class Director {
    Builder builder = null;

    public Director(Builder builder) {
        this.builder = builder;
    }

    public void construct(String board, String display) {
        builder.setBoard(board);
        builder.setDisplay(display);
        builder.buildOs();
    }
}
