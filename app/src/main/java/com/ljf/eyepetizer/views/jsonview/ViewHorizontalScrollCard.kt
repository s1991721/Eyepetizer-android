package com.ljf.eyepetizer.views.jsonview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.ljf.eyepetizer.R
import com.ljf.eyepetizer.model.ViewData
import com.ljf.eyepetizer.utils.JsonViewUtils
import kotlinx.android.synthetic.main.view_horizontalscrollcard.view.*

/**
 * Created by mr.lin on 2018/1/8.
 */
class ViewHorizontalScrollCard(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : BaseJsonView(context, attrs, defStyleAttr) {

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_horizontalscrollcard, this, true)
    }

    override fun initView() {
        var json = data.json
        val jsonArray = json.getJSONArray("itemList")
        //多次调用view会重复
        linearLayout.removeAllViews()
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            var view = JsonViewUtils.viewDataToView(context, ViewData(jsonObject))
            if (view != null) {
                linearLayout.addView(view)
            }
        }
    }


}