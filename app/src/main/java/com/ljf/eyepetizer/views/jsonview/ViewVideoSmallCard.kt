package com.ljf.eyepetizer.views.jsonview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.bumptech.glide.Glide
import com.ljf.eyepetizer.R
import com.ljf.eyepetizer.utils.CommonUtils
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

        Glide.with(context).load(json.getJSONObject("cover").getString("feed")).into(coverIv)

        durationTv.text = CommonUtils.secondsToMin(json.getString("duration").toInt())

        titleTv.text = json.getString("title")

        descriptionTv.text = String.format("#%s / %s", json.getString("category"), context.getString(R.string.quality_selected))
    }


}