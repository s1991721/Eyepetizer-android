package com.ljf.eyepetizer.manager

import com.google.gson.reflect.TypeToken
import com.ljf.eyepetizer.http.Requester
import com.ljf.eyepetizer.model.Category
import com.ljf.eyepetizer.utils.SpUtils

/**
 * Created by mr.lin on 2018/1/20.
 * 分类管理
 */
object CategoryManager {

    var categories = ArrayList<Category>()
    private var onGetCategoryListeners = ArrayList<OnGetCategoryListener>()

    init {
        var list = SpUtils.getList<List<Category>>(SpUtils.KEY_CATEGORY_LIST, object : TypeToken<List<Category>>() {}.type)

        if (list == null || categories.isEmpty()) {
            Requester.getCategories(object : Requester.OnResultListener<List<Category>> {
                override fun onResult(data: List<Category>?) {
                    if (data != null) {
                        categories.addAll(data)
                        notifyOnGetCatefoty()
                    }
                }
            })
        } else {
            categories.addAll(list)
        }
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