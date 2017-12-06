package com.lvfq.code.aidl;

import com.lvfq.code.IBankAIDL;

/**
 * BankBinder
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/12/6 下午4:42
 * @desc :
 */

public class BankBinder extends IBankAIDL.Stub{
    @Override
    public String openAccount(String name, String password) {
        return "开户成功，name :" + name + " ,password: " + password;
    }

    @Override
    public String saveMoney(int money, String account) {
        return "存钱，account :" + account + " ,money: " + money;
    }

    @Override
    public String takeMoney(int money, String account, String password) {
        return "取款 ， account : " + account + " , password: " + money;
    }

    @Override
    public String destroyAccount(String account, String password) {
        return "销户：account:" + account + " , password: " + password;
    }
}
