package com.ljf.eyepetizer.utils.glide

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.ljf.eyepetizer.utils.CommonUtils

/**
 * Created by mr.lin on 2018/1/14.
 * glide工具
 */
class GlideUtils {

    companion object {

        fun loadImage(context: Context, url: String, iv: ImageView) {
            Glide.with(context)
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .into(iv)
        }

        fun loadRoundImage(context: Context, url: String, iv: ImageView, radius: Int) {
            Glide.with(context)
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .transform(GlideRoundTransform(context, CommonUtils.dpTopx(radius.toFloat())))
                    .into(iv)
        }

        fun loadCircleImage(context: Context, url: String, iv: ImageView) {
            Glide.with(context)
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .transform(GlideCircleTransform(context))
                    .into(iv)
        }

    }

}