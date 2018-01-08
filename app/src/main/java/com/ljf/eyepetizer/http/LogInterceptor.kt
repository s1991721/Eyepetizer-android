package com.ljf.eyepetizer.http

import com.ljf.eyepetizer.utils.Logger
import okhttp3.Interceptor
import okhttp3.Response
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by mr.lin on 2017/12/28.
 * 打印日志的拦截器
 */
class LogInterceptor : Interceptor {

    val tag = "Retrofit"
    val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")

    override fun intercept(chain: Interceptor.Chain): Response {

        var request = chain.request()

        Logger.i(tag, format.format(Date()) + " Requeste " + "\nmethod:" + request.method() + "\nurl:" + request.url() + "\nbody:" + request.body())

        var response = chain.proceed(request)

        Logger.i(tag, format.format(Date()) + " Response " + "\nsuccessful:" + response.isSuccessful + "\nbody:" + response.peekBody(1024)?.string())

        return response
    }

}