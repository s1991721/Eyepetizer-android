package com.ljf.eyepetizer.http

import com.ljf.eyepetizer.http.model.CategoryResult
import com.ljf.eyepetizer.model.Category
import com.ljf.eyepetizer.model.FragmentContent
import com.ljf.eyepetizer.model.ViewData
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by mr.lin on 2017/12/28.
 * 网络请求类
 */
class Requester {

    companion object {

        private var services = HashMap<String, Any>()

        private fun <T> getService(baseUrl: String, service: Class<T>): T {

            if (services.containsKey(service.simpleName)) {
                return services[service.simpleName] as T
            }

            val client = OkHttpClient.Builder()
                    .addInterceptor(LogInterceptor())
                    .build()

            val retrofit = Retrofit.Builder().baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(client)
                    .build()

            var temp = retrofit.create(service)

            services.put(service.simpleName, temp!!)

            return temp
        }

        fun apiService(): ApiService {
            return getService(ApiService.baseUrl, ApiService::class.java)
        }

        fun getCategories(onResultListener: OnResultListener<List<Category>>) {
            apiService().getCategoryList().enqueue(object : Callback<CategoryResult> {
                override fun onFailure(call: Call<CategoryResult>?, t: Throwable?) {
                    onResultListener.onResult(null)
                }

                override fun onResponse(call: Call<CategoryResult>?, response: Response<CategoryResult>?) {
                    onResultListener.onResult(response?.body()?.tabInfo?.tabList)
                }
            })
        }

        fun getFragmentContent(url: String, onResultListener: OnResultListener<FragmentContent>) {
            apiService().getCategoryContent(url).enqueue(object : Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                    onResultListener.onResult(null)
                }

                override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                    onResultListener.onResult(toModel(response?.body()!!.string()))
                }
            })
        }

        private fun toModel(string: String): FragmentContent {
            val json = JSONObject(string)
            val jsonArray = json.getJSONArray("itemList")
            val viewDatas = ArrayList<ViewData>()
            for (i in 0 until jsonArray.length()) {
                val viewData = ViewData(jsonArray.getJSONObject(i))
                viewDatas.add(viewData)
            }

            return FragmentContent(json.getString("nextPageUrl"), viewDatas)
        }

    }

    interface OnResultListener<T> {
        fun onResult(data: T?)
    }

}