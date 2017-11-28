package com.lvfq.code.designpatterns.imageload;

import java.io.Closeable;
import java.io.IOException;

/**
 * CloseUtils
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/11/26 下午11:17
 * @desc :
 */

public class CloseUtils {

    public static void close(Closeable closeable) {
        if (null != closeable) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
