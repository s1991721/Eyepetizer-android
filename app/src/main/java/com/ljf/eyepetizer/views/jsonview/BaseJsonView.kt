package com.ljf.eyepetizer.views.jsonview

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.ljf.eyepetizer.model.ViewData

/**
 * Created by mr.lin on 2018/1/13.
 * JsonView的基类，所有都要继承于此
 */
abstract class BaseJsonView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : FrameLayout(context, attrs, defStyleAttr), JsonView {

    lateinit var data: ViewData

    override fun setViewData(viewData: ViewData) {
        data = viewData
        initView()
    }

}