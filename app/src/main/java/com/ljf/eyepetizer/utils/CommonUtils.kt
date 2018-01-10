package com.ljf.eyepetizer.utils

import com.ljf.eyepetizer.App

/**
 * Created by mr.lin on 2017/12/14.
 * 工具类
 */
class CommonUtils {

    companion object {
        fun dpTopx(dp: Float): Int {
            var density = App.instance().resources.displayMetrics.density
            return (dp * density + 0.5).toInt()
        }

        fun secondsToMin(seconds: Int): String {
            var min = seconds / 60
            var second = seconds % 60
            return min.toString() + ":" + second.toString()
        }
    }

}