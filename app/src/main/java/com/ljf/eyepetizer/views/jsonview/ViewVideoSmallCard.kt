package com.ljf.eyepetizer.views.jsonview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import com.ljf.eyepetizer.R
import com.ljf.eyepetizer.utils.CommonUtils
import kotlinx.android.synthetic.main.view_videosmallcard.view.*
import org.json.JSONObject

/**
 * Created by mr.lin on 2018/1/10.
 */
class ViewVideoSmallCard(context: Context, json: JSONObject, attrs: AttributeSet?, defStyleAttr: Int) : FrameLayout(context, attrs, defStyleAttr) {

    constructor(context: Context, json: JSONObject, attrs: AttributeSet?) : this(context, json, attrs, 0)
    constructor(context: Context, json: JSONObject) : this(context, json, null)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_videosmallcard, this, true)

        Glide.with(context).load(json.getJSONObject("cover").getString("feed")).into(coverIv)

        durationTv.text = CommonUtils.secondsToMin(json.getString("duration").toInt())

        titleTv.text = json.getString("title")

        descriptionTv.text = String.format("#%s / %s", json.getString("category"), context.getString(R.string.quality_selected))

    }
}