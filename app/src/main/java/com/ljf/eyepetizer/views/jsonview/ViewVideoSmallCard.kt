package com.ljf.eyepetizer.views.jsonview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.bumptech.glide.Glide
import com.ljf.eyepetizer.R
import com.ljf.eyepetizer.utils.CommonUtils
import com.ljf.eyepetizer.utils.glide.GlideUtils
import kotlinx.android.synthetic.main.view_videosmallcard.view.*

/**
 * Created by mr.lin on 2018/1/10.
 */
class ViewVideoSmallCard(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : BaseJsonView(context, attrs, defStyleAttr) {

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_videosmallcard, this, true)
    }


    override fun initView() {
        var json = data.json

        GlideUtils.loadRoundImage(context, json.optJSONObject("cover").optString("feed"), coverIv, 5)

        durationTv.text = CommonUtils.secondsToMin(json.optString("duration").toInt())

        titleTv.text = json.optString("title")

        descriptionTv.text = String.format("#%s / %s", json.optString("category"), context.getString(R.string.quality_selected))
    }


}