package com.ljf.eyepetizer.http

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by mr.lin on 2017/12/28.
 * 网络请求类
 */
class Requester {

    companion object {

        private fun <T> getService(baseUrl: String, service: Class<T>): T {

            val client = OkHttpClient.Builder()
                    .addInterceptor(LogInterceptor())
                    .build()

            val retrofit = Retrofit.Builder().baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(client)
                    .build()
            return retrofit.create(service)
        }

        fun apiService(): ApiService {
            return getService(ApiService.baseUrl, ApiService::class.java)
        }

    }

}