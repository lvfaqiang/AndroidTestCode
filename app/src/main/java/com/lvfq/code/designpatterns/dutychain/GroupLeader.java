package com.lvfq.code.designpatterns.dutychain;

/**
 * GroupLeader
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/11/29 下午11:51
 * @desc :
 */

public class GroupLeader extends Leader {

    @Override
    int limit() {
        return 1000;
    }

    @Override
    void handle(int money) {
        System.out.println("Group 审批了 " + money);
    }
}
