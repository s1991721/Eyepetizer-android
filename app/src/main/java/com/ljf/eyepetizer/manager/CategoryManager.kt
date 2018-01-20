package com.ljf.eyepetizer.manager

import com.google.gson.reflect.TypeToken
import com.ljf.eyepetizer.http.Requester
import com.ljf.eyepetizer.model.Category
import com.ljf.eyepetizer.utils.SpUtils
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by mr.lin on 2018/1/20.
 * 分类管理
 */
object CategoryManager {

    var categories = ArrayList<Category>()
    private var onGetCategoryListeners = ArrayList<OnGetCategoryListener>()

    init {
        categories.addAll(SpUtils.getList<List<Category>>(SpUtils.KEY_CATEGORY_LIST, object : TypeToken<List<Category>>() {}.type))
        if (categories.isEmpty()) {
            Requester.apiService().getCategoryList().enqueue(object : Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                }

                override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>) {
                    toCategory(response.body()!!.string())
                }
            })
        }
    }

    fun toCategory(json: String) {
        var jsonArry = JSONObject(json).getJSONObject("tabInfo").getJSONArray("tabList")
        categories.clear()
        for (i in 0 until jsonArry.length()) {
            var jsonObject = jsonArry.getJSONObject(i)
            var category = Category(jsonObject.getInt("id"), jsonObject.getString("name"), jsonObject.getString("apiUrl"))
            categories.add(category)
        }
        notifyOnGetCatefoty()
        SpUtils.setList(SpUtils.KEY_CATEGORY_LIST, categories)
    }

    interface OnGetCategoryListener {
        fun onGetCatefoty(categories: List<Category>)
    }

    fun addOnGetCategoryListener(listener: OnGetCategoryListener) {
        onGetCategoryListeners.add(listener)
    }

    fun removeOnGetCategoryListener(listener: OnGetCategoryListener) {
        onGetCategoryListeners.remove(listener)
    }

    private fun notifyOnGetCatefoty() {
        for (listener in onGetCategoryListeners) {
            listener.onGetCatefoty(categories)
        }
    }

}