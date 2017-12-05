package com.lvfq.code.thread;

/**
 * ThreadMain
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/12/5 上午11:58
 * @desc : 控制线程停止
 */

public class ThreadMain {

    public static void main(String[] args) {

        Thread1 thread1 = new Thread1();
        thread1.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 调用线程中断方法。
        thread1.interrupt();
    }


    static class Thread1 extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                if (interrupted()) {
                    System.out.println("线程停止了");
                    return;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
//                    e.printStackTrace();
                    /**
                     *  如果当前线程 被执行 interrupt 方法时，该线程正在处于休眠状态，那么则会抛出 java.lang.InterruptedException: sleep interrupted 异常
                     *  这里一定要处理线程停止的后续操作。
                     */
                    System.out.println("线程停止了");
                    return;
                }
                System.out.println("执行结果" + i);
            }

        }
    }

}

