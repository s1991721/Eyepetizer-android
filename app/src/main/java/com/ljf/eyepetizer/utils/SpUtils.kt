package com.ljf.eyepetizer.utils

import android.content.Context
import com.ljf.eyepetizer.App

/**
 * Created by mr.lin on 2017/12/21.
 * SharedPreferences
 */
class SpUtils {

    companion object {

        private val sharedPreferences = App.instance().getSharedPreferences("SpUtils", Context.MODE_PRIVATE)

        val KEY_FIRST_START="key_first_start"


        fun getInt(key: String, default: Int): Int {
            return sharedPreferences.getInt(key, default)
        }

        fun setInt(key: String, value: Int) {
            sharedPreferences.edit().putInt(key, value).apply()
        }
    }

}