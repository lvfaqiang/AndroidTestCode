package com.lvfq.code.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

/**
 * ImageLoadUtil
 *
 * @author lvfq
 * @date 2017/7/19 下午9:30
 * @mainFunction :
 */

public class Glide4_ImageLoadUtil {

    private static final int RADIUS = 4;

    /**
     * 初始化 Options 参数
     *
     * @param resourceId
     * @param errorResourceId
     * @return
     */
    private static RequestOptions initOptions(int resourceId, int errorResourceId) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(resourceId)
                .error(errorResourceId == 0 ? resourceId : errorResourceId)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        return options;
    }

    /**
     * 调用 Glide 加载
     *
     * @param context
     * @param options
     * @param url
     * @param imageView
     */
    private static void glideApply(Context context, RequestOptions options, Object url, ImageView imageView) {
        GlideApp.with(context)
                .applyDefaultRequestOptions(options)
                .load(url)
                .into(imageView);
    }

    /************* 默认加载方式 ****************/
    /**
     * 默认加载图片
     *
     * @param context
     * @param url
     * @param resourceId
     * @param imageView
     */
    public static void loadImg(Context context, Object url, int resourceId, ImageView imageView) {
        loadImg(context, url, resourceId, 0, imageView);
    }

    /**
     * 默认加载图片
     *
     * @param context
     * @param url
     * @param resourceId
     * @param imageView
     */
    public static void loadImg(Context context, Object url, int resourceId, int errorResourceId, ImageView imageView) {
        RequestOptions options = initOptions(resourceId, errorResourceId);
        glideApply(context, options, url, imageView);
    }

    /************* 加载圆形图 ****************/
    /**
     * 加载圆形图片
     *
     * @param context
     * @param url
     * @param resourceId
     * @param imageView
     */
    public static void loadCircleImg(Context context, Object url, int resourceId, ImageView imageView) {
        loadCircleImg(context, url, resourceId, 0, imageView);
    }

    /**
     * 加载圆形图片
     *
     * @param context
     * @param url
     * @param resourceId
     * @param errorResourceId 给 0 默认显示 resouceId
     * @param imageView
     */
    public static void loadCircleImg(Context context, Object url, int resourceId, int errorResourceId, ImageView imageView) {
        RequestOptions options = initOptions(resourceId, errorResourceId)
                .circleCrop();

        glideApply(context, options, url, imageView);
    }

    /************* 加载圆角图片 方式 1      ****************/


    /**
     * 加载圆角
     *
     * @param context
     * @param url
     * @param resourceId      占位图
     * @param errorResourceId 加载失败图
     * @param imageView
     * @param dp
     */
    public static void loadRoundImg(Context context, Object url, int resourceId, int errorResourceId, ImageView imageView, int dp) {
        RequestOptions options = initOptions(resourceId, errorResourceId)
                .transform(new RoundedCorners(dp));

        glideApply(context, options, url, imageView);
    }

    /**
     * 加载圆角
     *
     * @param context
     * @param url
     * @param resourceId 占位图（也是加载失败图）
     * @param imageView
     * @param dp
     */
    public static void loadRoundImg(Context context, Object url, int resourceId, ImageView imageView, int dp) {
        RequestOptions options = initOptions(resourceId, 0)
                .transform(new RoundedCorners(dp));

        glideApply(context, options, url, imageView);
    }

    /**
     * 加载圆角，默认圆角值为 4
     *
     * @param context
     * @param url
     * @param resourceId 占位图（也是加载失败图）
     * @param imageView
     */
    public static void loadRoundImg(Context context, Object url, int resourceId, ImageView imageView) {
        RequestOptions options = initOptions(resourceId, 0);
//                .transform(new RoundedCorners(RADIUS)).transform(new CenterCrop());

//        Glide.with(context)
//                .applyDefaultRequestOptions(options)
//                .load(url)
//                .into(imageView);
        glideApply(context, options, url, imageView);
    }


    /************* 加载圆角图片 方式 2      ****************/

    /**
     * 加载圆角图片
     *
     * @param context
     * @param url
     * @param resourceId      占位图
     * @param errorResourceId 加载失败图
     * @param imageView
     * @param dp
     * @param type
     */
//    public static void loadRoundImgCorner(Context context, Object url, int resourceId, int errorResourceId, ImageView imageView, int dp, RoundedCornersTransformation.CornerType type) {
//        RequestOptions options = initOptions(resourceId, errorResourceId)
//                .transform(new RoundedCornersTransformation(context, dp, 0, type));
//
//        glideApply(context, options, url, imageView);
//    }

    /**
     * 加载圆角图片 默认圆角值为 4
     *
     * @param context
     * @param url
     * @param resourceId      占位图
     * @param errorResourceId 加载失败图
     * @param imageView
     */
//    public static void loadRoundImgCorner(Context context, Object url, int resourceId, int errorResourceId, ImageView imageView) {
//        loadRoundImgCorner(context, url, resourceId, errorResourceId, imageView, RADIUS, RoundedCornersTransformation.CornerType.ALL);
//    }

    /**
     * 加载圆角图片
     *
     * @param context
     * @param url
     * @param resourceId 占位图（也是加载失败图）
     * @param imageView
     * @param dp
     */
//    public static void loadRoundImgCorner(Context context, Object url, int resourceId, ImageView imageView, int dp) {
//        loadRoundImgCorner(context, url, resourceId, 0, imageView, dp, RoundedCornersTransformation.CornerType.ALL);
//    }

    /**
     * 加载圆角图片
     *
     * @param context
     * @param url
     * @param resourceId      占位图
     * @param errorResourceId 加载失败图
     * @param imageView
     * @param dp
     */
//    public static void loadRoundImgCorner(Context context, Object url, int resourceId, int errorResourceId, ImageView imageView, int dp) {
//        loadRoundImgCorner(context, url, resourceId, errorResourceId, imageView, dp, RoundedCornersTransformation.CornerType.ALL);
//    }


}
