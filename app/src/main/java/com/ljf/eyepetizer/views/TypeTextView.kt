package com.ljf.eyepetizer.views

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.widget.TextView

/**
 * Created by mr.lin on 2017/12/25.
 * 打字效果
 */
class TypeTextView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : TextView(context, attrs, defStyleAttr) {

    lateinit var content: String
    lateinit var animator: ValueAnimator

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    fun setContentText(string: String) {
        content = string
        show()
    }

    fun show() {
        animator = ValueAnimator.ofInt(0, content.length)
        animator.duration = 1000
        animator.addUpdateListener { animation ->
            var len = animation?.animatedValue as Int
            text = content.substring(0, len)
        }
        animator.start()
    }

}