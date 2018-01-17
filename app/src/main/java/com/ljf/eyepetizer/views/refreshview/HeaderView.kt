package com.ljf.eyepetizer.views.refreshview

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

/**
 * Created by mr.lin on 2018/1/16.
 * RefreshRecyclerView统一header的父类
 */
abstract class HeaderView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : FrameLayout(context, attrs, defStyleAttr) {

    abstract fun setVisibleHeight(height: Float)

    abstract fun isEnoughtToRefresh(): Boolean

    abstract fun startRefresh()

    abstract fun endRefresh()

    abstract fun cancelRefresh()

}