package com.ljf.eyepetizer.views

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import com.ljf.eyepetizer.R

/**
 * Created by mr.lin on 2017/12/25.
 * 指示器
 */
class IndicatorView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : LinearLayout(context, attrs, defStyleAttr) {

    private var normalBG: Drawable
    private var selectBG: Drawable
    private var contentMargin: Int

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    init {
        gravity = Gravity.CENTER
        var typedArray = context.obtainStyledAttributes(attrs, R.styleable.IndicatorView)
        contentMargin = typedArray.getDimensionPixelSize(R.styleable.IndicatorView_indicatorView_margin, 15)

        var tempBG = typedArray.getDrawable(R.styleable.IndicatorView_indicatorView_normal)
        if (tempBG != null) {
            normalBG = tempBG
        } else {
            normalBG = ContextCompat.getDrawable(context, R.mipmap.ic_indicator_normal)
        }
        tempBG = typedArray.getDrawable(R.styleable.IndicatorView_indicatorView_checked)
        if (tempBG != null) {
            selectBG = tempBG
        } else {
            selectBG = ContextCompat.getDrawable(context, R.mipmap.ic_indicator_selected)
        }

        setSize(typedArray.getInt(R.styleable.IndicatorView_indicatorView_count, 0))
        typedArray.recycle()
    }

    fun setSize(size: Int) {
        removeAllViews()
        for (i in 0 until size) {
            var imageView = ImageView(context)
            var params = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
            params.leftMargin = contentMargin
            imageView.scaleType = ImageView.ScaleType.CENTER
            if (i == 0) {
                imageView.setImageDrawable(selectBG)
            } else {
                imageView.setImageDrawable(normalBG)
            }
            addView(imageView, params)
        }
    }

    fun select(position: Int) {
        if (position < childCount) {
            for (i in 0 until childCount) {
                var imageView: ImageView = getChildAt(i) as ImageView
                if (position == i) {
                    imageView.setImageDrawable(selectBG)
                } else {
                    imageView.setImageDrawable(normalBG)
                }
            }
        }
    }

}