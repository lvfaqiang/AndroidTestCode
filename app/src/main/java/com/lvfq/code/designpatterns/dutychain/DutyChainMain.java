package com.lvfq.code.designpatterns.dutychain;

/**
 * DutyChainMain
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/11/29 下午11:51
 * @desc :
 */

public class DutyChainMain {

    public static void main(String[] args) {
        GroupLeader group = new GroupLeader();
        Director director = new Director();
        ManagerLeader managerLeader = new ManagerLeader();
        BossLeader boss = new BossLeader();
        group.proLeader = director;
        director.proLeader = managerLeader;
        managerLeader.proLeader = boss;

        group.handlerRequest(8000);
    }
}
