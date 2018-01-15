package com.ljf.eyepetizer.utils.glide

import android.content.Context
import android.graphics.*
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation


/**
 * Created by mr.lin on 2018/1/14.
 * 圆角图片转换
 */
class GlideRoundTransform(context: Context, private var radius: Int) : BitmapTransformation(context) {

    override fun transform(pool: BitmapPool, toTransform: Bitmap, outWidth: Int, outHeight: Int): Bitmap? {

        return roundCrop(pool, toTransform)

    }

    private fun roundCrop(pool: BitmapPool, source: Bitmap): Bitmap {
        var result = pool.get(source.width, source.height, Bitmap.Config.ARGB_8888)
        if (result == null) {
            result = Bitmap.createBitmap(source.width, source.height, Bitmap.Config.ARGB_8888)
        }

        val canvas = Canvas(result)
        val paint = Paint()
        paint.shader = BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        paint.isAntiAlias = true
        val rectF = RectF(0f, 0f, source.width.toFloat(), source.height.toFloat())
        canvas.drawRoundRect(rectF, radius.toFloat(), radius.toFloat(), paint)
        return result
    }

    override fun getId(): String {
        return this.javaClass.name
    }

}