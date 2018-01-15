package com.ljf.eyepetizer.views.jsonview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import com.ljf.eyepetizer.R
import com.ljf.eyepetizer.utils.CommonUtils
import com.ljf.eyepetizer.utils.glide.GlideUtils
import kotlinx.android.synthetic.main.view_banner2.view.*

/**
 * Created by mr.lin on 2018/1/8.
 */
class ViewBanner2(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : BaseJsonView(context, attrs, defStyleAttr) {

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_banner2, this, true)

        layoutParams = LayoutParams(CommonUtils.dpTopx(330f), CommonUtils.dpTopx(190f))
        iv.scaleType = ImageView.ScaleType.FIT_CENTER
    }

    override fun initView() {
        val url = data.json.getString("image")

        GlideUtils.loadRoundImage(context, url, iv, 10)
    }

}