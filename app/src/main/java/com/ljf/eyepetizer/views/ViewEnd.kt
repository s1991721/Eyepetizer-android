package com.ljf.eyepetizer.views

import android.content.Context
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import com.ljf.eyepetizer.R
import com.ljf.eyepetizer.utils.CommonUtils
import com.ljf.eyepetizer.views.jsonview.BaseJsonView

/**
 * Created by mr.lin on 2018/1/11.
 * 底部结束
 */
class ViewEnd(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : BaseJsonView(context, attrs, defStyleAttr) {


    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)

    init {

        layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, CommonUtils.dpTopx(150f))

        val textView = TextView(context)
        addView(textView)

        textView.text = context.getString(R.string.the_end)
        textView.setTextColor(ContextCompat.getColor(context, R.color.c444444))
        textView.typeface = Typeface.createFromAsset(context.assets, "Lobster-1.4.otf")
        textView.paint.isFakeBoldText = true

        textView.gravity = Gravity.CENTER
    }

    override fun initView() {
    }

}