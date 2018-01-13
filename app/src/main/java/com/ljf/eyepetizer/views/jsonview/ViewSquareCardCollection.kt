package com.ljf.eyepetizer.views.jsonview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.ljf.eyepetizer.R
import com.ljf.eyepetizer.model.ViewData
import com.ljf.eyepetizer.utils.JsonViewUtils
import kotlinx.android.synthetic.main.view_squarecardcollection.view.*

/**
 * Created by mr.lin on 2018/1/10.
 */
class ViewSquareCardCollection(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : BaseJsonView(context, attrs, defStyleAttr) {

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_squarecardcollection, this, true)
    }

    override fun initView() {
        var json = data.json
        titleTv.text = json.getJSONObject("header").getString("title")

        var itemList = json.getJSONArray("itemList")
        containerLl.removeAllViews()
        for (i in 0 until itemList.length()) {
            var viewData = ViewData(itemList.getJSONObject(i))
            containerLl.addView(JsonViewUtils.viewDataToView(context, viewData))
        }
    }

}