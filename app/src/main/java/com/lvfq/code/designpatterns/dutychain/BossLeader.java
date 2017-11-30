package com.lvfq.code.designpatterns.dutychain;

/**
 * BossLeader
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/11/29 下午11:48
 * @desc :
 */

public class BossLeader extends Leader {

    @Override
    int limit() {
        return Integer.MAX_VALUE;
    }

    @Override
    void handle(int money) {
        System.out.println("Boss 审批了 " + money);
    }
}
