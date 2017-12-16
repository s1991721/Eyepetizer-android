package com.ljf.eyepetizer

import android.app.Application

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
    }
}