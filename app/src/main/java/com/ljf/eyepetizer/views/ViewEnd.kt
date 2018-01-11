package com.ljf.eyepetizer.views

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import com.ljf.eyepetizer.R
import com.ljf.eyepetizer.utils.CommonUtils

/**
 * Created by mr.lin on 2018/1/11.
 */
class ViewEnd(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : TextView(context, attrs, defStyleAttr) {

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)

    init {

        layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, CommonUtils.dpTopx(150f))

        text = context.getString(R.string.the_end)
        typeface = Typeface.createFromAsset(context.assets, "Lobster-1.4.otf")
        paint.isFakeBoldText = true

        gravity = Gravity.CENTER
    }

}