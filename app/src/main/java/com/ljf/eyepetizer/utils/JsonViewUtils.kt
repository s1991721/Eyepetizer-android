package com.ljf.eyepetizer.utils

import android.content.Context
import android.view.View
import com.ljf.eyepetizer.model.ViewData
import com.ljf.eyepetizer.views.jsonview.*

/**
 * Created by mr.lin on 2018/1/8.
 * 将JsonView工具类
 */
class JsonViewUtils {

    companion object {

        val TYPE_HORIZONTALSCROLLCARD = 1
        val TYPE_BANNER2 = 2
        val TYPE_TEXTCARD = 3
        val TYPE_BRIEFCARD = 4
        val TYPE_FOLLOWCARD = 5
        val TYPE_VIDEOSMALLCARD = 6
        val TYPE_SQUARECARDCOLLECTION = 7
        val TYPE_VIDEOCOLLECTIONWITHBRIEF = 8
        val TYPE_VIDEO = 9
        val TYPE_DYNAMICINFOCARD = 10

        fun viewDataToView(context: Context, viewData: ViewData): View? {
            var view: BaseJsonView? = null
            when (viewData.type) {
                "horizontalScrollCard" -> view = ViewHorizontalScrollCard(context)
                "banner2" -> view = ViewBanner2(context)
                "textCard" -> view = ViewTextCard(context)
                "briefCard" -> view = ViewBriefCard(context)
                "followCard" -> view = ViewFollowCard(context)
                "videoSmallCard" -> view = ViewVideoSmallCard(context)
                "squareCardCollection" -> view = ViewSquareCardCollection(context)
                "videoCollectionWithBrief" -> view = ViewVideoCollectionWithBrief(context)
                "video" -> view = ViewVideo(context)
                "DynamicInfoCard" -> view = ViewDynamicInfoCard(context)
            }
            if (view != null) {
                injectView(view, viewData)
            }
            return view
        }

        fun viewDataGetType(viewData: ViewData): Int {
            when (viewData.type) {
                "horizontalScrollCard" -> return TYPE_HORIZONTALSCROLLCARD
                "banner2" -> return TYPE_BANNER2
                "textCard" -> return TYPE_TEXTCARD
                "briefCard" -> return TYPE_BRIEFCARD
                "followCard" -> return TYPE_FOLLOWCARD
                "videoSmallCard" -> return TYPE_VIDEOSMALLCARD
                "squareCardCollection" -> return TYPE_SQUARECARDCOLLECTION
                "videoCollectionWithBrief" -> return TYPE_VIDEOCOLLECTIONWITHBRIEF
                "video" -> return TYPE_VIDEO
                "DynamicInfoCard" -> return TYPE_DYNAMICINFOCARD
            }
            return -1
        }

        fun viewTypeGetView(context: Context, viewType: Int): BaseJsonView? {
            when (viewType) {
                TYPE_HORIZONTALSCROLLCARD -> return ViewHorizontalScrollCard(context)
                TYPE_BANNER2 -> return ViewBanner2(context)
                TYPE_TEXTCARD -> return ViewTextCard(context)
                TYPE_BRIEFCARD -> return ViewBriefCard(context)
                TYPE_FOLLOWCARD -> return ViewFollowCard(context)
                TYPE_VIDEOSMALLCARD -> return ViewVideoSmallCard(context)
                TYPE_SQUARECARDCOLLECTION -> return ViewSquareCardCollection(context)
                TYPE_VIDEOCOLLECTIONWITHBRIEF -> return ViewVideoCollectionWithBrief(context)
                TYPE_VIDEO -> return ViewVideo(context)
                TYPE_DYNAMICINFOCARD -> return ViewDynamicInfoCard(context)
            }
            return null
        }

        fun injectView(view: BaseJsonView, viewData: ViewData) {
            view.setViewData(viewData)
        }

    }

}