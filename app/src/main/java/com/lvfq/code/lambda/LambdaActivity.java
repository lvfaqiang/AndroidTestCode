package com.lvfq.code.lambda;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lvfq.library.utils.LvLog;

/**
 * LambdaActivity
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/9/12 下午3:48
 * @desc :
 */

public class LambdaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Thread(() -> {
            LvLog.i("测试线程。。。");
        }).start();


        Runnable runnable = () -> {
            LvLog.i("测试 。。。Runnable");
        };

        MyListener listener = (a, b) -> {
//            LvLog.i(" a: " + a + "  ,b: " + b);
            return a + b;
        };
        // 使用 MyListener 作为参数
        hello((a, b) -> {
            return a + b;
        });

        testListener(s -> {
            return "fdfdfdfd";
        });
    }

    private void hello(MyListener listener) {
        String result = listener.doSomething("a ", "b");
        LvLog.i("result ： " + result);
    }

    private void testListener(Listener listener) {
        LvLog.i(listener.doThing("ceshi listener"));
    }


    public interface MyListener {
        String doSomething(String a, String b);
    }

    interface Listener{
        String doThing(String s);
    }


}
