package com.lvfq.code.util;

import android.graphics.Bitmap;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * ImageUtils
 *
 * @author lvfq
 * @date 2017/4/19 下午2:35
 * @mainFunction :
 */

public class ImageUtils {
    /**
     * 把图片村保存在相应的文件当中
     *
     * @param pBitmap
     * @param fileName
     */
    public static void saveFile(Bitmap pBitmap, String fileName) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(fileName);
            pBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            Log.i("jiangqq", "保存图片到sdcard卡成功.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
