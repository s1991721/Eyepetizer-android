package com.ljf.eyepetizer

import android.app.Application
import com.ljf.eyepetizer.utils.CacheUtils
import java.io.File
import java.io.FileOutputStream

/**
 * Created by mr.lin on 2017/12/14.
 * Application
 */
class App : Application() {

    companion object {

        private var instance: Application? = null

        fun instance() = instance!!

    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        init()
    }

    fun init() {
        initAssets()
    }

    //将资源从包中转移到存储
    fun initAssets() {
        if (!isFileExist()) {
            translateFile()
        }
    }

    fun isFileExist(): Boolean {
        var file = File(CacheUtils.getFileDir() + "landing.mp4")
        return file.exists()
    }

    fun translateFile() {
        var inputStream = assets.open("landing.mp4")
        var outputStream = FileOutputStream(CacheUtils.getFileDir() + "landing.mp4")
        var byteArray = ByteArray(512)
        var len: Int
        while (inputStream.read(byteArray) != -1) {
            outputStream.write(byteArray)
        }
        outputStream.flush()
        outputStream.close()
        inputStream.close()
    }
}