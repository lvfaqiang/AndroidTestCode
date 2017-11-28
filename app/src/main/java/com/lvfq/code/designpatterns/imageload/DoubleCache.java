package com.lvfq.code.designpatterns.imageload;

import android.graphics.Bitmap;

/**
 * DoubleCache
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/11/26 上午11:47
 * @desc :
 */

public class DoubleCache implements ImageCache {
    private MemoryCache memoryCache = new MemoryCache();
    private DiskCache diskCache = new DiskCache();

    @Override
    public Bitmap get(String url) {
        Bitmap bitmap = null;
        bitmap = memoryCache.get(url);
        if (bitmap == null) {
            bitmap = diskCache.get(url);
        }
        return bitmap;
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        memoryCache.put(url, bitmap);
        diskCache.put(url, bitmap);
    }
}
