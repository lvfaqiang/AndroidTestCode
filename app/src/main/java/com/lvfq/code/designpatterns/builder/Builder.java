package com.lvfq.code.designpatterns.builder;

/**
 * Builder
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/11/27 下午11:33
 * @desc :
 */

public abstract class Builder {
    public abstract void setBoard(String board);

    public abstract void setDisplay(String display);

    public abstract void buildOs();

    public abstract Computer create();
}
