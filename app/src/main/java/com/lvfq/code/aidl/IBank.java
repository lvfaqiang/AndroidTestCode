package com.lvfq.code.aidl;

/**
 * IBank
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/12/6 下午4:39
 * @desc :
 */

public interface IBank {
    /**
     * 开户
     *
     * @param name
     * @param password
     * @return
     */
    String openAccount(String name, String password);

    /**
     * 存钱
     *
     * @param money
     * @param account
     * @return
     */
    String saveMoney(int money, String account);

    /**
     * 取钱
     *
     * @param money
     * @param account
     * @param password
     * @return
     */
    String takeMoney(int money, String account, String password);

    /**
     * 销户
     *
     * @param account
     * @param password
     * @return
     */
    String destroyAccount(String account, String password);

}
