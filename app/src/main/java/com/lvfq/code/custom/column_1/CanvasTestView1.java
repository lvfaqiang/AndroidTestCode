package com.lvfq.code.custom.column_1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;

import com.lvfq.code.R;
import com.lvfq.library.utils.LvDPUtil;

/**
 * CanvasTestView1
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/10/23 下午2:18
 * @desc :
 * 参考博客 https://juejin.im/post/5962a3746fb9a06ba2687226
 */

public class CanvasTestView1 extends View {

    private Paint paint = new Paint();

    public CanvasTestView1(Context context) {
        super(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /**
         *  -------------------- 画圆 --------------------
         */
        // 画一个红色的圆
        paint.setColor(getResources().getColor(R.color.red));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(LvDPUtil.dip2px(5));
        paint.setAntiAlias(true);   // 开启抗锯齿
        canvas.drawCircle(300, 300, 100, paint);
//        canvas.drawColor(Color.parseColor("#88880000"));
//        canvas.drawRGB(100, 100, 200);

        // 画一个黑色方块，和 黑色边框的矩形
//        paint.setColor(Color.BLACK);
//        paint.setStyle(Paint.Style.FILL_AND_STROKE);
//        canvas.drawRect(100, 100, 500, 500, paint);
//
//        paint.setStyle(Paint.Style.STROKE);
//        canvas.drawRect(600, 100, 800, 500, paint);

        /**
         * ROUND 绘制线的时候，两端是圆角的，
         * SQUARE 绘制线的时候两端是方形的，
         * BUTT 绘制线的时候 两端和 SQUARE效果是一样的，但是相对于 ROUND、SQUARE， BUTT 两端分别会短{StrokeWidth} 的宽度
         */

        // 绘制圆点
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawPoint(100, 100, paint);
        canvas.drawLine(50, 500, 300, 500, paint);
        // 绘制方形点
        paint.setStrokeCap(Paint.Cap.SQUARE);
        canvas.drawPoint(300, 300, paint);
        canvas.drawLine(50, 600, 300, 600, paint);
        //绘制平头
        paint.setStrokeCap(Paint.Cap.BUTT);
//        paint.setStrokeWidth(50);
        canvas.drawPoint(600, 100, paint);
        canvas.drawLine(50, 700, 300, 700, paint);


        /**
         *  -------------------- 画线 --------------------
         */
        // 一次性画多条线  必须是 4 的倍数
        float[] lines = new float[]{400, 500, 800, 500, 600, 500, 600, 700, 400, 700, 800, 700};
        canvas.drawLines(lines, paint);


        paint.setColor(Color.BLACK);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setAntiAlias(true);
        float[] points = new float[]{80, 80, 240, 80, 80, 240, 240, 240};

        // points 必须是被2整除的 ， 第二个参数 2 表示跳过 points 前面两个数字，第三个参数 6 表示画出后面6个数字，也就是三个点，
        canvas.drawPoints(points, 2, 6, paint);


        /**
         *  -------------------- 画椭圆 --------------------
         */

        // 绘制椭圆 四个点组成一个椭圆
        paint.setStrokeWidth(5);
        canvas.drawOval(500, 100, 700, 250, paint);

        RectF rectF = new RectF(500, 150, 700, 300);   // RectF 是用来封装 上下左右四个点坐标
        canvas.drawOval(rectF, paint);


        /**
         *  -------------------- 画矩形 --------------------
         */

        // 绘制两个圆角矩形。
        RectF roundF = new RectF(50, 800, 200, 900);
        canvas.drawRoundRect(roundF, 20, 20, paint);
//        canvas.drawRoundRect(50, 800, 200, 900, 20, 20, paint);  // 等同于上面
        RectF roundF1 = new RectF(250, 800, 400, 900);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRoundRect(roundF1, 20, 20, paint);   // 实心矩形


        /**
         *  -------------------- 画扇形 --------------------
         */
        /**
         drawArc(float left, float top, float right, float bottom, float startAngle, float sweepAngle, boolean useCenter, Paint paint) 绘制弧形或扇形
         drawArc() 是使用一个椭圆来描述弧形的。left, top, right, bottom 描述的是这个弧形所在的椭圆；
         startAngle 是弧形的起始角度（x 轴的正向，即正右的方向，是 0 度的位置；顺时针为正角度，逆时针为负角度），
         sweepAngle 是弧形划过的角度；
         useCenter 表示是否连接到圆心，如果不连接到圆心，就是弧形，如果连接到圆心，就是扇形
         */

        RectF rect = new RectF(50, 1000, 450, 1200);
        canvas.drawArc(rect, 0, 120, true, paint);
        canvas.drawArc(rect, 140, 100, false, paint);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(rect, -60, 60, true, paint);

    }
}
