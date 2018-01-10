package com.ljf.eyepetizer.views.jsonview

import android.content.Context
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.ljf.eyepetizer.R
import com.ljf.eyepetizer.utils.CommonUtils
import org.json.JSONObject

/**
 * Created by mr.lin on 2018/1/7.
 */
class ViewTextCard(context: Context?, json: JSONObject, attrs: AttributeSet?, defStyleAttr: Int) : TextView(context, attrs, defStyleAttr) {

    constructor(context: Context, json: JSONObject, attrs: AttributeSet?) : this(context, json, attrs, 0)
    constructor(context: Context, json: JSONObject) : this(context, json, null)

    init {

        when (json.getString("type")) {

            "header5" -> {
                textSize = 20f
                paint.isFakeBoldText = true
                setTextColor(ContextCompat.getColor(context, R.color.c444444))
                setPadding(CommonUtils.dpTopx(15f), CommonUtils.dpTopx(30f), 0, 0)

                gravity = Gravity.LEFT or Gravity.CENTER_VERTICAL

                if (!TextUtils.isEmpty(json.getString("actionUrl"))) {
                    var drawable = ContextCompat.getDrawable(context, R.mipmap.goto_icon)
                    drawable.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight)
                    setCompoundDrawables(null, null, drawable, null)
                }

                layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            }

            "footer2" -> {
                textSize = 12f
                paint.isFakeBoldText = true
                setTextColor(ContextCompat.getColor(context, R.color.c444444))
                gravity = Gravity.RIGHT or Gravity.CENTER_VERTICAL

                var drawable = ContextCompat.getDrawable(context, R.mipmap.goto_icon)
                drawable.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight)
                setCompoundDrawables(null, null, drawable, null)

                setPadding(0, CommonUtils.dpTopx(20f), 0, 0)
            }

        }

        text = json.getString("text")
    }

}