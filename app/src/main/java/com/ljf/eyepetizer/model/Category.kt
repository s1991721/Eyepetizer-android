package com.ljf.eyepetizer.model

import java.io.Serializable

/**
 * Created by mr.lin on 2018/1/19.
 * 分类实体
 */
data class Category(var id: Int, var name: String, var apiUrl: String) : Serializable