package com.lvfq.code.designpatterns.dutychain;

/**
 * ManagerLeader
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/11/29 下午11:49
 * @desc :
 */

public class ManagerLeader extends Leader {
    @Override
    int limit() {
        return 10000;
    }

    @Override
    void handle(int money) {
        System.out.println("Manager 审批了 " + money);
    }
}
