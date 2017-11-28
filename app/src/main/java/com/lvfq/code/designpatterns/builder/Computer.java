package com.lvfq.code.designpatterns.builder;

/**
 * Computer
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/11/27 下午11:13
 * @desc :
 */

public abstract class Computer {
    protected String mBoard;    // 主板
    protected String mDisplay;  // 显示器
    protected String mOS;   // 系统

    public Computer() {
    }

    public void setBoard(String board) {
        mBoard = board;
    }

    public void setDisplay(String display) {
        mDisplay = display;
    }

    public abstract void setOs();
}
