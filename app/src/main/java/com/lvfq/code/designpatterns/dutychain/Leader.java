package com.lvfq.code.designpatterns.dutychain;

/**
 * Leader
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/11/29 下午11:45
 * @desc :
 */

public abstract class Leader {
    protected Leader proLeader;


    // 最大可审批额度
    abstract int limit();

    // 处理报账请求
    void handlerRequest(int monty) {
        // 如果当前领导权限范围内，则直接处理。超出权限范围，则递交上级领导处理
        if (monty <= limit()) {
            handle(monty);
        } else {
            proLeader.handlerRequest(monty);
        }
    }

    // 处理报账行为
    abstract void handle(int money);
}
