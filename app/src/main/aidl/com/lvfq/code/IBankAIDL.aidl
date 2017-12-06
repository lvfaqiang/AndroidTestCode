// IBankAIDL.aidl
package com.lvfq.code;

// Declare any non-default types here with import statements

/**
 * 右键新建 AIDL 文件，然后自动生成 main 目录下的 aidl 包，然后 make project (commond + F9)
 * 然后使用的时候就直接继承 IBankAiDL.Stub
 *
**/
interface IBankAIDL {
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
