package com.ljf.eyepetizer.views.jsonview

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import android.widget.LinearLayout
import com.ljf.eyepetizer.model.ViewData
import com.ljf.eyepetizer.utils.CommonUtils
import com.ljf.eyepetizer.utils.JsonViewUtils
import org.json.JSONObject

/**
 * Created by mr.lin on 2018/1/8.
 */
class ViewHorizontalScrollCard(context: Context, json: JSONObject, attrs: AttributeSet?) : HorizontalScrollView(context, attrs) {

    constructor(context: Context, json: JSONObject) : this(context, json, null)

    init {

        var linearLayout = LinearLayout(context)
        linearLayout.orientation = LinearLayout.HORIZONTAL

        val jsonArray = json.getJSONArray("itemList")
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            var view = JsonViewUtils.viewDataToView(context, ViewData(jsonObject))
            if (view != null) {
                linearLayout.addView(view)
            }
        }

        linearLayout.setPadding(CommonUtils.dpTopx(7f), 0, CommonUtils.dpTopx(15f), 0)

        isHorizontalScrollBarEnabled = false

        addView(linearLayout)

    }


}