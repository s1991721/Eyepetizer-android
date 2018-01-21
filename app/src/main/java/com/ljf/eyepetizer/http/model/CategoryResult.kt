package com.ljf.eyepetizer.http.model

import com.ljf.eyepetizer.model.Category

/**
 * Created by mr.lin on 2018/1/21.
 * 获取分类的接口返回
 */
data class TabInfo(var tabList: List<Category>)

data class CategoryResult(var tabInfo: TabInfo)

