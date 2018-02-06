package com.ljf.eyepetizer.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.ljf.eyepetizer.R
import com.ljf.eyepetizer.activity.BaseActivity
import kotlinx.android.synthetic.main.view_actionbar.view.*

/**
 * Created by mr.lin on 2018/2/6.
 * 标题栏
 */
class ActionBar(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : FrameLayout(context, attrs, defStyleAttr) {

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_actionbar, this, true)

        backIb.setOnClickListener { (context as BaseActivity).finish() }
    }

    fun setOnBackClickListener(clickListener: OnClickListener) {
        backIb.setOnClickListener(clickListener)
    }

    fun setTitle(title: String) {
        titleTv.text = title
    }

    fun setTitle(id: Int) {
        titleTv.text = context.getString(id)
    }

}