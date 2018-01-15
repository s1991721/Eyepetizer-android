package com.ljf.eyepetizer.utils

import com.ljf.eyepetizer.App
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by mr.lin on 2017/12/14.
 * 工具类
 */
class CommonUtils {

    companion object {

        //像素比转换
        fun dpTopx(dp: Float): Int {
            var density = App.instance().resources.displayMetrics.density
            return (dp * density + 0.5).toInt()
        }

        //秒转分
        fun secondsToMin(seconds: Int): String {
            var min = seconds / 60
            var second = seconds % 60
            return min.toString() + ":" + second.toString()
        }

        //时间戳转时间
        fun timestampToTime(timestamp: Long): String {
            var format = SimpleDateFormat("yyyy-MM-dd HH:mm")
            var date = Date(timestamp)
            return format.format(date)
        }

        //不超过现在24小时
        fun isTodayAround(timestamp: Long): Boolean {
            var diffent = 24 * 60 * 60 * 1000
            var today = System.currentTimeMillis()
            return Math.abs(today - timestamp) < diffent
        }

        //显示最近时间
        fun showCurrentTime(timestamp: Long): String {
            var format: SimpleDateFormat
            var date = Date(timestamp)
            if (isTodayAround(timestamp)) {
                format = SimpleDateFormat("mm:ss")
            } else {
                format = SimpleDateFormat("yyyy-MM-dd")
            }
            return format.format(date)
        }
    }

}