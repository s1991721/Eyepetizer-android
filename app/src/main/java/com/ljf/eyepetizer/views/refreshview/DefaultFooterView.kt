package com.ljf.eyepetizer.views.refreshview

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.animation.*
import android.widget.LinearLayout
import com.ljf.eyepetizer.R
import com.ljf.eyepetizer.utils.CommonUtils
import kotlinx.android.synthetic.main.view_defaultheaderview.view.*

/**
 * Created by mr.lin on 2018/1/15.
 * 默认FooterView
 */
class DefaultFooterView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : FooterView(context, attrs, defStyleAttr) {

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_defaultheaderview, this)

        var params = LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 0)
        params.gravity = Gravity.CENTER
        layoutParams = params
    }

}