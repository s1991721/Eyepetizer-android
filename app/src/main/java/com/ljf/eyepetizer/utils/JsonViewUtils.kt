package com.ljf.eyepetizer.utils

import android.content.Context
import android.view.View
import com.ljf.eyepetizer.model.ViewData
import com.ljf.eyepetizer.views.jsonview.*

/**
 * Created by mr.lin on 2018/1/8.
 * 将json转成View
 */
class JsonViewUtils {

    companion object {
        fun viewDataToView(context: Context, viewData: ViewData): View? {
            when (viewData.type) {
                "horizontalScrollCard" -> return ViewHorizontalScrollCard(context, viewData.json)
                "banner2" -> return ViewBanner2(context, viewData.json)
                "textCard" -> return ViewTextCard(context, viewData.json)
                "briefCard" -> return ViewBriefCard(context, viewData.json)
                "followCard" -> return ViewFollowCard(context, viewData.json)
                "videoSmallCard" -> return ViewVideoSmallCard(context, viewData.json)
                "squareCardCollection" -> return ViewSquareCardCollection(context, viewData.json)
                "videoCollectionWithBrief" -> return ViewVideoCollectionWithBrief(context, viewData.json)
                "video" -> return ViewVideo(context, viewData.json)
                "DynamicInfoCard" -> return ViewDynamicInfoCard(context, viewData.json)
            }
            return null
        }
    }

}