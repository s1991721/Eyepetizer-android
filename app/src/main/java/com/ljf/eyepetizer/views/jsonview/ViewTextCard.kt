package com.ljf.eyepetizer.views.jsonview

import android.content.Context
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ljf.eyepetizer.R
import com.ljf.eyepetizer.utils.CommonUtils
import kotlinx.android.synthetic.main.view_textcard.view.*

/**
 * Created by mr.lin on 2018/1/7.
 */
class ViewTextCard(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : BaseJsonView(context, attrs, defStyleAttr) {

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_textcard, this)
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
    }

    override fun initView() {
        var json = data.json

        when (json.getString("type")) {

            "header5" -> {
                tv.textSize = 20f
                tv.paint.isFakeBoldText = true
                tv.setTextColor(ContextCompat.getColor(context, R.color.c444444))
                setPadding(CommonUtils.dpTopx(15f), CommonUtils.dpTopx(30f), 0, 0)

                tv.gravity = Gravity.LEFT or Gravity.CENTER_VERTICAL

                if ("null" != (json.getString("actionUrl"))) {
                    var drawable = ContextCompat.getDrawable(context, R.mipmap.goto_icon)
                    drawable.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight)
                    tv.setCompoundDrawables(null, null, drawable, null)
                } else {
                    tv.setCompoundDrawables(null, null, null, null)
                }

            }

            "footer2" -> {
                tv.textSize = 12f
                tv.paint.isFakeBoldText = true
                tv.setTextColor(ContextCompat.getColor(context, R.color.c444444))
                tv.gravity = Gravity.RIGHT or Gravity.CENTER_VERTICAL

                var drawable = ContextCompat.getDrawable(context, R.mipmap.goto_icon)
                drawable.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight)
                tv.setCompoundDrawables(null, null, drawable, null)

                setPadding(0, CommonUtils.dpTopx(20f), 0, 0)
            }

        }

        tv.text = json.getString("text")
    }

}