package com.lvfq.code.payment

import android.os.Build
import android.support.annotation.DrawableRes
import android.text.InputType
import android.widget.EditText
import android.widget.TextView
import java.lang.reflect.Method

/**
 * GlobalExt
 * @author FaQiang on 2018/7/10 上午9:29
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 *
 */

/**
 * 设置 TextView 显示图标
 */
fun TextView.draw(@DrawableRes res: Int?, position: Position) {
    val drawable = if (res != null) resources.getDrawable(res) else {
        null
    }
    drawable?.let {
        drawable.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight)
    }
    when (position) {
        Position.LEFT -> {
            setCompoundDrawables(drawable, null, null, null)
        }
        Position.RIGHT -> {
            setCompoundDrawables(null, null, drawable, null)
        }
        Position.TOP -> {
            setCompoundDrawables(null, drawable, null, null)
        }
        Position.BOTTOM -> {
            setCompoundDrawables(null, null, null, drawable)
        }
    }
}

enum class Position {
    LEFT, RIGHT, TOP, BOTTOM
}


fun hideSystemSoftKeyboard(editText: EditText) {
    val sdkInt = Build.VERSION.SDK_INT
    if (sdkInt >= 11) {
        try {
            val cls = EditText::class.java
            val setShowSoftInputOnFocus: Method
            setShowSoftInputOnFocus = cls.getMethod("setShowSoftInputOnFocus", Boolean::class.javaPrimitiveType)
            setShowSoftInputOnFocus.isAccessible = true
            setShowSoftInputOnFocus.invoke(editText, false)
        } catch (e: SecurityException) {
            e.printStackTrace()
        } catch (e: NoSuchMethodException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    } else {
        editText.inputType = InputType.TYPE_NULL
    }
}