package com.ljf.eyepetizer.views.jsonview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import com.ljf.eyepetizer.R
import com.ljf.eyepetizer.model.ViewData
import com.ljf.eyepetizer.utils.JsonViewUtils
import kotlinx.android.synthetic.main.view_videocollectionwithbrief.view.*
import org.json.JSONObject

/**
 * Created by mr.lin on 2018/1/10.
 */
class ViewVideoCollectionWithBrief(context: Context, json: JSONObject, attrs: AttributeSet?, defStyleAttr: Int) : FrameLayout(context, attrs, defStyleAttr) {

    constructor(context: Context, json: JSONObject, attrs: AttributeSet?) : this(context, json, attrs, 0)
    constructor(context: Context, json: JSONObject) : this(context, json, null)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_videocollectionwithbrief, this, true)

        var header = json.getJSONObject("header")

        Glide.with(context).load(header.getString("icon")).into(iconIv)

        titleTv.text = header.getString("title")

        descriptionTv.text = header.getString("description")

        var itemList = json.getJSONArray("itemList")
        for (i in 0 until itemList.length()) {
            var viewData = ViewData(itemList.getJSONObject(i))
            containerLl.addView(JsonViewUtils.viewDataToView(context, viewData))
        }

    }

}