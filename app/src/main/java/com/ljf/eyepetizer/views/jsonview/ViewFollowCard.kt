package com.ljf.eyepetizer.views.jsonview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import com.ljf.eyepetizer.R
import com.ljf.eyepetizer.utils.CommonUtils
import kotlinx.android.synthetic.main.view_followcard.view.*
import org.json.JSONObject

/**
 * Created by mr.lin on 2018/1/9.
 */
class ViewFollowCard(context: Context, json: JSONObject, attrs: AttributeSet?, defStyleAttr: Int) : FrameLayout(context, attrs, defStyleAttr) {

    constructor(context: Context, json: JSONObject, attrs: AttributeSet?) : this(context, json, attrs, 0)
    constructor(context: Context, json: JSONObject) : this(context, json, null)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_followcard, this, true)


        var contentData = json.getJSONObject("content").getJSONObject("data")
        var header = json.getJSONObject("header")

        Glide.with(context).load(contentData.getJSONObject("cover").getString("feed")).into(coverIv)

        durationTv.text = CommonUtils.secondsToMin(contentData.getInt("duration"))

        Glide.with(context).load(contentData.getJSONObject("author").getString("icon")).into(iconIv)

        titleTv.text = contentData.getString("title")

        descriptionTv.text = String.format("%s / %s", header.getString("title"), header.getString("description").split("/")[0])
    }

}