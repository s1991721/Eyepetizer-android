package com.ljf.eyepetizer.views.jsonview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.bumptech.glide.Glide
import com.ljf.eyepetizer.R
import com.ljf.eyepetizer.utils.CommonUtils
import com.ljf.eyepetizer.utils.glide.GlideUtils
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

        GlideUtils.loadRoundImage(context, contentData.getJSONObject("cover").getString("feed"), coverIv, 10)

        durationTv.text = CommonUtils.secondsToMin(contentData.getInt("duration"))

        GlideUtils.loadCircleImage(context, contentData.getJSONObject("author").getString("icon"), iconIv)

        titleTv.text = contentData.getString("title")

        if (header.getString("description").contains("收录于")) {
            descriptionTv.text = String.format("%s / %s", header.getString("title"), header.getString("description").split("/")[0])
        } else {
            descriptionTv.text = header.getString("description")
        }
    }
}