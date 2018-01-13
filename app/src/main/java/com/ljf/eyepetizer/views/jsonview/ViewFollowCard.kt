package com.ljf.eyepetizer.views.jsonview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.bumptech.glide.Glide
import com.ljf.eyepetizer.R
import com.ljf.eyepetizer.utils.CommonUtils
import kotlinx.android.synthetic.main.view_followcard.view.*

/**
 * Created by mr.lin on 2018/1/9.
 */
class ViewFollowCard(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : BaseJsonView(context, attrs, defStyleAttr) {

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_followcard, this, true)
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
    }

    override fun initView() {
        var json = data.json
        var contentData = json.getJSONObject("content").getJSONObject("data")
        var header = json.getJSONObject("header")

        Glide.with(context).load(contentData.getJSONObject("cover").getString("feed")).into(coverIv)

        durationTv.text = CommonUtils.secondsToMin(contentData.getInt("duration"))

        Glide.with(context).load(contentData.getJSONObject("author").getString("icon")).into(iconIv)

        titleTv.text = contentData.getString("title")

        descriptionTv.text = String.format("%s / %s", header.getString("title"), header.getString("description").split("/")[0])
    }
}