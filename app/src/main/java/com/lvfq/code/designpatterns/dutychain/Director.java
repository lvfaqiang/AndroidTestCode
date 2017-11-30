package com.lvfq.code.designpatterns.dutychain;

/**
 * Director
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/11/29 下午11:50
 * @desc :
 */

public class Director extends Leader {
    @Override
    int limit() {
        return 5000;
    }

    @Override
    void handle(int money) {
        System.out.println("Director 审批了 " + money);
    }
}
