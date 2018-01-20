package com.ljf.eyepetizer.utils

import android.content.Context
import com.google.gson.Gson
import com.ljf.eyepetizer.App
import java.lang.reflect.Type

/**
 * Created by mr.lin on 2017/12/21.
 * SharedPreferences
 */
class SpUtils {

    companion object {

        private val sharedPreferences = App.instance().getSharedPreferences("SpUtils", Context.MODE_PRIVATE)

        //是否首次启动
        val KEY_FIRST_START = "key_first_start"
        //分类列表
        val KEY_CATEGORY_LIST = "key_category_list"

        private val gson = Gson()

        fun getInt(key: String, default: Int): Int {
            return sharedPreferences.getInt(key, default)
        }

        fun setInt(key: String, value: Int) {
            sharedPreferences.edit().putInt(key, value).apply()
        }

        fun getString(key: String, default: String): String {
            return sharedPreferences.getString(key, default)
        }

        fun setString(key: String, value: String) {
            sharedPreferences.edit().putString(key, value).apply()
        }

        fun <T> getObject(key: String, cls: Class<T>): T {
            var value = getString(key, "")
            return gson.fromJson<T>(value, cls)
        }

        fun setObject(key: String, obj: Any) {
            var value = gson.toJson(obj)
            setString(key, value)
        }

        //Java中的泛型基本上都是在编译器这个层次来实现的。在生成的Java字节码中是不包含泛型中的类型信息的。
        //使用泛型的时候加上的类型参数，会在编译器在编译的时候去掉。这个过程就称为类型擦除。
        //https://segmentfault.com/q/1010000009644038
        //所以这里采用传递type的方式，否则会由于找不到类型，而序列化失败
        //从这一点看之前的JsonHelper就不会有这种问题
        fun <T> getList(key: String, type: Type): T {
            var value = getString(key, "")
            return gson.fromJson(value, type)
        }

        fun <T> setList(key: String, datas: List<T>) {
            var value = gson.toJson(datas)
            setString(key, value)
        }
    }

}