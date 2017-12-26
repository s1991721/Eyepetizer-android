package com.ljf.eyepetizer.utils

import com.ljf.eyepetizer.App

/**
 * Created by mr.lin on 2017/12/24.
 * 缓存工具
 */
class CacheUtils {

    companion object {
        fun getCacheDir(): String {
            return App.instance().cacheDir.path
        }

        fun getFileDir(): String {
            return App.instance().filesDir.path
        }
    }

}