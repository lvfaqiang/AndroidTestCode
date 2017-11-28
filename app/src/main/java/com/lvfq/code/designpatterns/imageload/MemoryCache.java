package com.lvfq.code.designpatterns.imageload;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * MemoryCache
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/11/26 上午11:33
 * @desc : 内存缓存
 */

public class MemoryCache implements ImageCache {
    private LruCache<String, Bitmap> mCache;

    public MemoryCache() {
        initImageCache();
    }

    /**
     * 初始化缓存内存
     */
    private void initImageCache() {
        // 计算可使用的最大内存
        int maxCacheSize = (int) (Runtime.getRuntime().maxMemory() / 1024);
        int cache = maxCacheSize / 4;
        mCache = new LruCache<String, Bitmap>(cache) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
//               （api 1 ） value.getRowBytes() * value.getHeight() 用于计算位图在内存中所占用的字节数， = (api 12)value.getByteCount 也就是计算位图的大小
                return value.getRowBytes() * value.getHeight() / 1024;
            }
        };
    }


    @Override
    public Bitmap get(String url) {
        return mCache.get(url);
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        mCache.put(url, bitmap);
    }
}
