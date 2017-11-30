package com.lvfq.code.map

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * MapActivity
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/11/29 下午8:54
 * @desc : 亲测
 *
 */
class MapActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * 百度地图包名：com.baidu.BaiduMap
         * 高德地图包名：com.autonavi.minimap
         * 腾讯地图包名：com.tencent.map
         *
         * 用来判断是否存在当前应用
         */
        val bd = "com.baidu.BaiduMap"
        val gd = "com.autonavi.minimap"
        val tx = "com.tencent.map"


    }

    /**
     * 唤醒手机上的 百度地图
     *纬度，经度，标题
     *  参考链接： http://lbsyun.baidu.com/index.php?title=uri/api/android
     */
    private fun startBd(lat: String, long: String, title: String) {
        val intent = Intent()
        intent.data = Uri.parse("baidumap://map/marker?location=$lat,$long&title=$title");
        startActivity(intent)
    }

    /**
     * 唤醒手机上的 高德地图
     *
     * 参考地址 http://lbs.amap.com/api/amap-mobile/guide/android/marker
     */
    private fun startGd(lat: String, long: String, title: String) {
        val intent = Intent()
        intent.data = Uri.parse("androidamap://viewMap?sourceApplication=${applicationInfo.name}&poiname=$title&lat=$lat&lon=$long&dev=1");
        startActivity(intent)
    }
}