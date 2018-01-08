package com.ljf.eyepetizer.utils

import android.util.Log
import com.ljf.eyepetizer.BuildConfig

/**
 * Created by mr.lin on 2017/12/28.
 * 日志打印
 */
class Logger {

    companion object {
        fun i(tag: String, msg: String) {
            if (BuildConfig.DEBUG) {
                Log.i(tag, msg)
            }
        }
    }

}