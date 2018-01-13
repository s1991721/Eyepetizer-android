package com.ljf.eyepetizer.views.jsonview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.bumptech.glide.Glide
import com.ljf.eyepetizer.R
import com.ljf.eyepetizer.model.ViewData
import com.ljf.eyepetizer.utils.JsonViewUtils
import kotlinx.android.synthetic.main.view_videocollectionwithbrief.view.*

/**
 * Created by mr.lin on 2018/1/10.
 */
class ViewVideoCollectionWithBrief(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : BaseJsonView(context, attrs, defStyleAttr) {

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_videocollectionwithbrief, this)
    }

    override fun initView() {
        var json = data.json

        var header = json.getJSONObject("header")

        Glide.with(context).load(header.getString("icon")).into(iconIv)

        titleTv.text = header.getString("title")

        descriptionTv.text = header.getString("description")

        var itemList = json.getJSONArray("itemList")
        containerLl.removeAllViews()
        for (i in 0 until itemList.length()) {
            var viewData = ViewData(itemList.getJSONObject(i))
            containerLl.addView(JsonViewUtils.viewDataToView(context, viewData))
        }
    }

}