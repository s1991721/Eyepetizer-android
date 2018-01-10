package com.ljf.eyepetizer.views.jsonview

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.ljf.eyepetizer.utils.CommonUtils
import org.json.JSONObject

/**
 * Created by mr.lin on 2018/1/8.
 */
class ViewBanner2(context: Context, json: JSONObject, attrs: AttributeSet?, defStyleAttr: Int) : ImageView(context, attrs, defStyleAttr) {

    constructor(context: Context, json: JSONObject, attrs: AttributeSet?) : this(context, json, attrs, 0)
    constructor(context: Context, json: JSONObject) : this(context, json, null)

    init {
        val url = json.getString("image")
        scaleType = ScaleType.FIT_CENTER

        Glide.with(context).load(url).into(this)

        setPadding(CommonUtils.dpTopx(7f), 0, 0, 0)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(CommonUtils.dpTopx(330f), CommonUtils.dpTopx(190f))
    }

}