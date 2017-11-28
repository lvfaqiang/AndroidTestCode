package com.lvfq.code.designpatterns.builder;

/**
 * MacBookBuilder
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/11/27 下午11:34
 * @desc :
 */

public class MacBookBuilder extends Builder {

    private Computer computer = new MacBook();

    @Override
    public void setBoard(String board) {
        computer.setBoard(board);
    }

    @Override
    public void setDisplay(String display) {
        computer.setDisplay(display);
    }

    @Override
    public void buildOs() {
        computer.setOs();
    }

    @Override
    public Computer create() {
        return computer;
    }
}
