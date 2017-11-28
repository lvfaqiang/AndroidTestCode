package com.lvfq.code.designpatterns.imageload;

import android.graphics.Bitmap;

/**
 * ImageCache
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/11/26 上午11:29
 * @desc :
 */

public interface ImageCache {
    Bitmap get(String url);

    void put(String url, Bitmap bitmap);
}
