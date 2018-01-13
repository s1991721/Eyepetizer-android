package com.ljf.eyepetizer.views.jsonview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.bumptech.glide.Glide
import com.ljf.eyepetizer.R
import kotlinx.android.synthetic.main.view_briefcard.view.*

/**
 * Created by mr.lin on 2018/1/8.
 */
class ViewBriefCard(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : BaseJsonView(context, attrs, defStyleAttr) {

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_briefcard, this, true)
    }

    override fun initView() {
        var json = data.json

        Glide.with(context).load(json.getString("icon")).into(iconIv)

        titleTv.text = json.getString("title")

        descriptionTv.text = json.getString("description")
    }
}