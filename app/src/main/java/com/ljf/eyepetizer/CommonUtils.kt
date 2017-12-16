package com.ljf.eyepetizer

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
    }

}