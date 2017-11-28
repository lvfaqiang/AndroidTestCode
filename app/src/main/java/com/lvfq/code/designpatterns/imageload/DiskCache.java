package com.lvfq.code.designpatterns.imageload;

import android.graphics.Bitmap;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * DiskCache
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/11/26 上午11:36
 * @desc : SD卡缓存
 */

public class DiskCache implements ImageCache {

    static String cacheDir = "sdcard/cache/";

    @Override
    public Bitmap get(String url) {
        return null;
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(cacheDir + url);
            // bitmap 按照指定格式，将图片转换为 输出流，也就是这里会输出到 fileOutputStream 指定位置
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            CloseUtils.close(fileOutputStream);
        }
    }

}
