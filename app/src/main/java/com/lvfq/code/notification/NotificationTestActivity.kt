package com.lvfq.code.notification

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_ONE_SHOT
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.os.Bundle
import android.os.Vibrator
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationCompat.*
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.lvfq.code.R
import kotlinx.android.synthetic.main.activity_notification_test.*


/**
 * NotificationTestActivity
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/11/20 下午11:14
 * @desc :
 *
 */
class NotificationTestActivity : AppCompatActivity() {

    private val textView by lazy {
        TextView(this).apply {
            layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT).apply {
                setMargins(10, 10, 10, 10)
            }
            setPadding(20, 20, 20, 20)
            gravity = Gravity.CENTER
            text = "点击发送通知"
        }
    }

    private lateinit var notificationManager: NotificationManager
    private lateinit var v: Vibrator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_test)
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        v = getSystemService(Service.VIBRATOR_SERVICE) as Vibrator

        textView.setOnClickListener {
            initNotification()
        }

        btn_1.setOnClickListener {
            initNotification()
        }

        btn_2.setOnClickListener {
            initNotification1()
        }

        btn_3.setOnClickListener {
            //单独调用系统提示音
            val notifyUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val mRingtone = RingtoneManager.getRingtone(this, notifyUri)
            mRingtone.play()
//            mRingtone.stop()
        }

        btn_4.setOnClickListener {
            //            initNotification4()
            // 调用系统震动
            v.vibrate(1000)
//            v.cancel()
        }

    }

    /**
     * 初始化 Notification
     */
    private fun initNotification() {
        val intent = Intent(this, NotificationIntentActivity::class.java)
        intent.putExtra("testContent", "这是测试内容")
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, FLAG_ONE_SHOT)

        val def = DEFAULT_SOUND
        val noti = NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.img_notification)    //设置小图标，用于状态栏左上角显示
                .setContentTitle("我是标题")    //设置标题
                .setContentText("我是文本内容，这里是用来感受一下样式效果的")    //设置内容
                .setDefaults(def)    // 设置提醒样式
                .setTicker("一闪而过")  //  通知到来时状态栏闪过的信息,
                .setNumber(10)      //  //已经被 setSubText取代，显示在时间下方的内容
                .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.img_default)) // 设置大图标 用于通知栏里显示
                .setAutoCancel(true)    //
                .setContentIntent(pendingIntent)
                .build()
        notificationManager.notify(1, noti) // notify Id 相同的消息会被覆盖
    }

    private fun initNotification1() {
        val def = DEFAULT_SOUND or DEFAULT_VIBRATE
        val noti = NotificationCompat.Builder(this)
                .setSmallIcon(this.applicationInfo.icon)
                .setContentTitle("测试第二条消息")
                .setContentText("我是文本内容，这里是用来感受一下样式效果的")
                .setDefaults(def)    // 设置声音
                .setTicker("一闪而过")
                .setAutoCancel(true)    //
                .build()
        notificationManager.notify(2, noti)
    }

    private fun initNotification3() {
        val def = DEFAULT_VIBRATE
        val noti = NotificationCompat.Builder(this)
                .setSmallIcon(this.applicationInfo.icon)
                .setContentTitle("还是第一条消息")
                .setContentText("我是文本内容，这里是用来感受一下样式效果的")
                .setDefaults(def)
                .setTicker("一闪而过")
                .setAutoCancel(true)    //
                .build()
        notificationManager.notify(1, noti) // notify Id 相同的消息会被覆盖
    }

    private fun initNotification4() {
        val noti = NotificationCompat.Builder(this)
                .setSmallIcon(this.applicationInfo.icon)
                .setContentTitle("测试第 3 条消息")
                .setContentText("我是文本内容，这里是用来感受一下样式效果的")
                .setDefaults(DEFAULT_SOUND or DEFAULT_VIBRATE or DEFAULT_LIGHTS)
                .setTicker("一闪而过")
                .setAutoCancel(true)    //
                .build()
        notificationManager.notify(3, noti)
    }

}