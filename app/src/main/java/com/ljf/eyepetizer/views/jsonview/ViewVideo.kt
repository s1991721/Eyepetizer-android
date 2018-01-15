package com.ljf.eyepetizer.views.jsonview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.bumptech.glide.Glide
import com.ljf.eyepetizer.R
import com.ljf.eyepetizer.utils.CommonUtils
import com.ljf.eyepetizer.utils.glide.GlideUtils
import kotlinx.android.synthetic.main.view_video.view.*

/**
 * Created by mr.lin on 2018/1/10.
 */
class ViewVideo(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : BaseJsonView(context, attrs, defStyleAttr) {

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_video, this, true)
    }

    override fun initView() {
        var json = data.json

        GlideUtils.loadRoundImage(context,json.getJSONObject("cover").getString("feed"),coverIv,10)

        durationTv.text = CommonUtils.secondsToMin(json.getInt("duration"))

        descriptionTv.text = json.getString("title")

        categoryTv.text = String.format("# %s", json.getString("category"))
    }

}