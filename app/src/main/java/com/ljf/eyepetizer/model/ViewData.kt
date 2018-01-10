package com.ljf.eyepetizer.model

import org.json.JSONObject

/**
 * Created by mr.lin on 2018/1/7.
 * 控件数据
 */
data class ViewData(private var jsonObject: JSONObject) {
    var type = jsonObject.getString("type")
    var json = jsonObject.getJSONObject("data")
}