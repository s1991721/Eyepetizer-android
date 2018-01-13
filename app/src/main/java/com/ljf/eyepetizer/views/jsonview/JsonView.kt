package com.ljf.eyepetizer.views.jsonview

import com.ljf.eyepetizer.model.ViewData

/**
 * Created by mr.lin on 2018/1/13.
 * JsonView的通用接口
 */
interface JsonView {

    //设置控件数据
    fun setViewData(viewData: ViewData)

    //初始化view
    fun initView()

}