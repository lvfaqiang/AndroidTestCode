package com.lvfq.code;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import com.lvfq.code.util.FileUtils;
import com.lvfq.code.util.ImageUtils;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * ImageWaterMarkActivity
 *
 * @author lvfq
 * @date 2017/4/19 上午10:26
 * @mainFunction :
 */

public class ImageWaterMarkActivity extends FragmentActivity {

    public static String path = Environment.getExternalStorageDirectory() + File.separator + "Android" + File.separator
            + "_test" + File.separator;


    private ImageView iv_img;

    private Uri imgPath;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_water_mark);
        iv_img = (ImageView) findViewById(R.id.iv_img);


    }

    public void onClick(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        imgPath = Uri.fromFile(new File(mImageFileCachePath() + "img.jpg"));
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imgPath);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }

        if (requestCode == 1) {
            int angle = FileUtils.readPictureDegree(imgPath.getEncodedPath());
            Log.i("lfq", "angle = " + angle);
            try {
                Bitmap bitmap = createBitmap(BitmapFactory.decodeStream(getContentResolver().openInputStream(imgPath)), "添加的水印文字");
                ImageUtils.saveFile(bitmap, imgPath.getEncodedPath());
                Bitmap bitmap1 = FileUtils.ratingImage(imgPath.getEncodedPath(), bitmap);

                iv_img.setImageBitmap(bitmap);
//                iv_img.setImageBitmap(bitmap1);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

//            bitmap.recycle();
//            bitmap1.recycle();
        }
    }

    /**
     * @param src   添加水印的图片
     * @param title 水印文字
     * @return
     */
    public Bitmap createBitmap(Bitmap src, String title) {
        if (src == null) {
            return src;
        }
        Bitmap bitmap = src;
        // 获取原始图片的宽与高
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Bitmap newBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas mCanvas = new Canvas(newBitmap);
        // 往位图中开始画入src原始图片
        mCanvas.drawBitmap(bitmap, 0, 0, null);
        // 开始加入文字
        if (null != title) {
            Paint textPaint = new Paint();

            textPaint.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 48, getResources().getDisplayMetrics()));
            String familyName = "宋体";
            Typeface typeface = Typeface.create(familyName,
                    Typeface.NORMAL);
            textPaint.setTypeface(typeface);
            textPaint.setTextAlign(Paint.Align.CENTER);
            float textHeight = textPaint.descent() - textPaint.ascent();

            textPaint.setAntiAlias(true);
            textPaint.setColor(Color.BLACK);
            textPaint.setStrokeWidth(textHeight + dip2px(8));
            textPaint.setAlpha((int) (255 * 0.8));
            mCanvas.drawLine(0, h - (textHeight / 2 + dip2px(4)), w, h - (textHeight / 2 + dip2px(4)), textPaint); // 先画背景。
            textPaint.setColor(Color.WHITE);
            mCanvas.drawText(title, w / 2, h - (textHeight / 2) + dip2px(20), textPaint);

        }
        mCanvas.save(Canvas.ALL_SAVE_FLAG);
        mCanvas.restore();
        bitmap.recycle();
        return newBitmap;
    }


    public static String mImageFileCachePath() {
        String s = Environment.getExternalStorageDirectory() + File.separator + "Android" + File.separator
                + "_test" + File.separator;
        File file = new File(s);
        if (!file.exists()) {
            file.mkdirs();
        }
        return s;
    }


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public int dip2px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
