package com.ljf.eyepetizer.http

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by mr.lin on 2017/12/28.
 * 请求接口
 */
interface ApiService {

    companion object {
        val baseUrl = "http://baobab.kaiyanapp.com/"
    }

    @GET("api/v5/index/tab/discovery")
    fun getTabDiscovery(): Call<ResponseBody>

}