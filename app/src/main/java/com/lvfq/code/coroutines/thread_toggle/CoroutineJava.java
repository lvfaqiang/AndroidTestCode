package com.lvfq.code.coroutines.thread_toggle;

import org.jetbrains.annotations.NotNull;

import kotlin.Unit;
import kotlin.coroutines.experimental.Continuation;
import kotlin.coroutines.experimental.CoroutineContext;

/**
 * CoroutineJava
 *
 * @author FaQiang on 2018/8/27 上午9:57
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 */
public class CoroutineJava {

    public static void main(String[] args) {
        test();
        CoroutineUtils.start();
        System.out.println("main Function");
    }


    public static void test() {
        async(new Task() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int i = "string".hashCode();
                ui(new UI() {
                    @Override
                    public void run() {
                        System.out.println("------- UI - " + Thread.currentThread().getName());
                    }
                });
            }
        });
    }

    public static void async(Task task) {
        CoroutineUtils.async(task, new Continuation<Unit>() {
            @NotNull
            @Override
            public CoroutineContext getContext() {
                return null;
            }

            @Override
            public void resume(Unit unit) {

            }

            @Override
            public void resumeWithException(Throwable throwable) {

            }
        });
    }

    public static void ui(UI task) {
        CoroutineUtils.ui(task, new Continuation<Unit>() {
            @NotNull
            @Override
            public CoroutineContext getContext() {
                return null;
            }

            @Override
            public void resume(Unit unit) {

            }

            @Override
            public void resumeWithException(Throwable throwable) {

            }
        });
    }
}
