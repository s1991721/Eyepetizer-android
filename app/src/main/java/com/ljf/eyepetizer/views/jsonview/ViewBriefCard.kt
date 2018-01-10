package com.ljf.eyepetizer.views.jsonview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import com.ljf.eyepetizer.R
import kotlinx.android.synthetic.main.view_briefcard.view.*
import org.json.JSONObject

/**
 * Created by mr.lin on 2018/1/8.
 */
class ViewBriefCard(context: Context, json: JSONObject, attrs: AttributeSet?, defStyleAttr: Int) : FrameLayout(context, attrs, defStyleAttr) {

    constructor(context: Context, json: JSONObject, attrs: AttributeSet?) : this(context, json, attrs, 0)
    constructor(context: Context, json: JSONObject) : this(context, json, null)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_briefcard, this, true)

        Glide.with(context).load(json.getString("icon")).into(iconIv)

        titleTv.text=json.getString("title")

        descriptionTv.text=json.getString("description")

    }


}