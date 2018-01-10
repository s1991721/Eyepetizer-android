package com.ljf.eyepetizer.views.jsonview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.ljf.eyepetizer.R
import com.ljf.eyepetizer.model.ViewData
import com.ljf.eyepetizer.utils.JsonViewUtils
import kotlinx.android.synthetic.main.view_squarecardcollection.view.*
import org.json.JSONObject

/**
 * Created by mr.lin on 2018/1/10.
 */
class ViewSquareCardCollection(context: Context, json: JSONObject, attrs: AttributeSet?, defStyleAttr: Int) : FrameLayout(context, attrs, defStyleAttr) {

    constructor(context: Context, json: JSONObject, attrs: AttributeSet?) : this(context, json, attrs, 0)
    constructor(context: Context, json: JSONObject) : this(context, json, null)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_squarecardcollection, this, true)

        titleTv.text = json.getJSONObject("header").getString("title")

        var itemList = json.getJSONArray("itemList")
        for (i in 0 until itemList.length()) {
            var viewData = ViewData(itemList.getJSONObject(i))
            containerLl.addView(JsonViewUtils.viewDataToView(context, viewData))
        }

    }

}