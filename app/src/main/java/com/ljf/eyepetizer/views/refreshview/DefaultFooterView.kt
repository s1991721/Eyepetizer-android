package com.ljf.eyepetizer.views.refreshview

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.animation.Animation
import android.widget.LinearLayout
import com.ljf.eyepetizer.R
import kotlinx.android.synthetic.main.view_defaultheaderview.view.*

/**
 * Created by mr.lin on 2018/1/15.
 * 默认FooterView
 */
class DefaultFooterView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : AttachView(context, attrs, defStyleAttr) {

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)

    private var footerHeight = viewHeight

    private lateinit var rotateAnimator1: ObjectAnimator
    private lateinit var rotateAnimator2: ObjectAnimator
    private lateinit var rotateAnimator3: ObjectAnimator
    private lateinit var rotateAnimator4: ObjectAnimator
    private lateinit var animatorSet: AnimatorSet

    private var valueAnimator: ValueAnimator = ValueAnimator()

    init {
        LayoutInflater.from(context).inflate(R.layout.view_defaultheaderview, this)

        val params = LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 0)
        params.gravity = Gravity.CENTER
        layoutParams = params
    }

    override fun setVisibleHeight(height: Float) {
        if (height > viewHeight * 3) {
            return
        }
        val params = layoutParams
        params.height = height.toInt()
        layoutParams = params
        onAttachViewHeightChangeListener?.onHeightChange(Math.round(height))
    }

    override fun isEnought(): Boolean {
        val currentHeight = layoutParams.height
        return currentHeight >= footerHeight / 2
    }

    override fun start() {
        changeHeight(layoutParams.height, footerHeight, {}, { startRotate() })
    }

    override fun end() {
        changeHeight(layoutParams.height, 0, { stopRotate() }, {})
    }

    override fun cancel() {
        changeHeight(layoutParams.height, 0, {}, {})
    }

    private fun changeHeight(currentHeight: Int, target: Int, start: () -> Unit, end: () -> Unit) {
        if (valueAnimator.isRunning) {
            valueAnimator.cancel()
        }
        valueAnimator = ValueAnimator.ofInt(currentHeight, target)
        valueAnimator.duration = 500
        valueAnimator.addUpdateListener {
            val height = valueAnimator.animatedValue as Int
            val params = layoutParams
            params.height = height
            layoutParams = params
            onAttachViewHeightChangeListener?.onHeightChange(height)
        }
        valueAnimator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {
                start()
            }

            override fun onAnimationEnd(animation: Animator?) {
                end()
            }
        })
        valueAnimator.start()
    }

    private fun startRotate() {
        initRotate()
        animatorSet.start()
    }

    private fun initRotate() {
        rotateAnimator1 = ObjectAnimator.ofFloat(iv1, "rotation", 0f, 360f).setDuration(1000)
        rotateAnimator1.repeatCount = Animation.INFINITE
        rotateAnimator2 = ObjectAnimator.ofFloat(iv2, "rotation", 0f, 360f).setDuration(1000)
        rotateAnimator2.repeatCount = Animation.INFINITE
        rotateAnimator3 = ObjectAnimator.ofFloat(iv3, "rotation", 0f, 360f).setDuration(1000)
        rotateAnimator3.repeatCount = Animation.INFINITE
        rotateAnimator4 = ObjectAnimator.ofFloat(iv4, "rotation", 0f, 360f).setDuration(1000)
        rotateAnimator4.repeatCount = Animation.INFINITE
        animatorSet = AnimatorSet()
        animatorSet.play(rotateAnimator1)
        animatorSet.play(rotateAnimator4).after(200)
        animatorSet.play(rotateAnimator3).after(400)
        animatorSet.play(rotateAnimator2).after(600)
    }

    private fun stopRotate() {
        animatorSet.end()
        animatorSet.cancel()
    }

}