package com.ljf.eyepetizer.views.jsonview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.ljf.eyepetizer.R
import com.ljf.eyepetizer.utils.glide.GlideUtils
import kotlinx.android.synthetic.main.view_banner.view.*

/**
 * Created by mr.lin on 2018/2/5.
 */
class ViewBanner(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : BaseJsonView(context, attrs, defStyleAttr) {


    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_banner, this, true)
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
    }

    override fun initView() {
        var json = data.json
        GlideUtils.loadRoundImage(context, json.optString("image"), coverIv, 10)
    }

}