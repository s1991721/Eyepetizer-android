package com.ljf.eyepetizer.views.jsonview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import com.ljf.eyepetizer.R
import com.ljf.eyepetizer.utils.CommonUtils
import kotlinx.android.synthetic.main.view_video.view.*
import org.json.JSONObject

/**
 * Created by mr.lin on 2018/1/10.
 */
class ViewVideo(context: Context, json: JSONObject, attrs: AttributeSet?, defStyleAttr: Int) : FrameLayout(context, attrs, defStyleAttr) {

    constructor(context: Context, json: JSONObject, attrs: AttributeSet?) : this(context, json, attrs, 0)
    constructor(context: Context, json: JSONObject) : this(context, json, null)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_video, this, true)

        Glide.with(context).load(json.getJSONObject("cover").getString("feed")).into(coverIv)

        durationTv.text = CommonUtils.secondsToMin(json.getInt("duration"))

        descriptionTv.text = json.getString("title")

        categoryTv.text = String.format("# %s", json.getString("category"))
    }

}