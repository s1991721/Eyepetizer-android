package com.ljf.eyepetizer.views

import android.animation.ObjectAnimator
import android.content.Context
import android.support.v7.widget.AppCompatTextView
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.style.CharacterStyle
import android.text.style.UpdateAppearance
import android.util.AttributeSet

/**
 * Created by mr.lin on 2017/12/27.
 * 幕布式TextView
 */
class CurtainTextView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : AppCompatTextView(context, attrs, defStyleAttr) {

    private var animator: ObjectAnimator

    lateinit var spanList: ArrayList<MutableForegroundColorSpan>

    lateinit var spannableString: SpannableString

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)

    init {
        animator = ObjectAnimator.ofFloat(this, "textAlpha", 0f, 1f)
        animator.duration = 1000
        animator.addUpdateListener { animation -> text = spannableString }
    }

    fun setContentText(string: String) {
        spannableString = SpannableString(string)
        spanList = ArrayList()
        for (i in 0 until string.length) {
            var span = MutableForegroundColorSpan()
            spanList.add(span)
            spannableString.setSpan(span, i, i + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        animator.start()
    }


    class MutableForegroundColorSpan : CharacterStyle(), UpdateAppearance {
        var alpha = 0

        override fun updateDrawState(tp: TextPaint) {
            tp.alpha = alpha
        }

    }

    fun setTextAlpha(alpha: Float) {
        var size = spanList.size
        var total = size * alpha
        for (i in 0 until size) {
            var span = spanList.get(i)
            if (total >= 1) {
                span.alpha = 255
                --total
            } else {
                span.alpha = (255 * total).toInt()
                total = 0f
            }
        }
    }

}