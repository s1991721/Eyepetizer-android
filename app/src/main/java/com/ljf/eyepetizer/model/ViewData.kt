package com.ljf.eyepetizer.model

import org.json.JSONObject

/**
 * Created by mr.lin on 2018/1/7.
 * 控件数据
 */
data class ViewData(private var jsonObject: JSONObject) {

    constructor(type: String) : this(JSONObject("{\"type\": \"${type}\", \"data\": \"{}\"}"))

    var type = jsonObject.getString("type")
    var json = jsonObject.optJSONObject("data")
}