package com.lvfq.code.designpatterns.imageload;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ImageLoader
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/11/25 下午4:09
 * @desc :
 */

public class ImageLoader {
    // 图片缓存
    private ImageCache mImageCache = new MemoryCache();
    // 线程池
    ExecutorService mService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());


    public void setImageCache(ImageCache imageCache) {
        mImageCache = imageCache;
    }

    /**
     * 加载图片
     *
     * @param url
     * @param imageView
     */
    public void display(String url, ImageView imageView) {
        // 先获取一下缓存
        Bitmap bitmap = mImageCache.get(url);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
            return;
        }

        imageView.setTag(url);
        mService.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = downloadImage(url);
                if (bitmap == null) {
                    return;
                }
                if (imageView.getTag().equals(url)) {
                    imageView.setImageBitmap(bitmap);
                }
                mImageCache.put(url, bitmap);
            }
        });

    }

    /**
     * 下载图片
     *
     * @param imageUrl
     * @return
     */
    private Bitmap downloadImage(String imageUrl) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            bitmap = BitmapFactory.decodeStream(connection.getInputStream());
            connection.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

}
