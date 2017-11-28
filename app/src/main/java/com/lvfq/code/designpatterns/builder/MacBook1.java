package com.lvfq.code.designpatterns.builder;

/**
 * MacBook1
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/11/27 下午11:46
 * @desc :
 */

public class MacBook1 extends Computer {
    @Override
    public void setOs() {
        mOS = "Mac OS";
    }

    @Override
    public String toString() {
        return "MacBook1: board:" + mBoard + ", display:" + mDisplay + " , OS:" + mOS;
    }

    public static class MacBuilder {
        private String mboard;
        private String display;

        public MacBuilder setBoard(String board) {
            mboard = board;
            return this;
        }

        public MacBuilder setDisplay(String display) {
            this.display = display;
            return this;
        }

        public Computer create() {
            MacBook1 book1 = new MacBook1();
            book1.setBoard(mboard);
            book1.setDisplay(display);
            book1.setOs();
            return book1;
        }
    }

}
