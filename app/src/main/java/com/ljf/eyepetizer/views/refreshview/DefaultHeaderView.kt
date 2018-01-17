package com.ljf.eyepetizer.views.refreshview

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.animation.*
import android.view.animation.Animation.INFINITE
import android.widget.LinearLayout
import com.ljf.eyepetizer.R
import com.ljf.eyepetizer.utils.CommonUtils
import kotlinx.android.synthetic.main.view_defaultheaderview.view.*

/**
 * Created by mr.lin on 2018/1/15.
 * 默认HeaderView
 */
class DefaultHeaderView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : HeaderView(context, attrs, defStyleAttr) {

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)

    private var headerHeight = CommonUtils.dpTopx(50f)

    private var rotateAnimation1: Animation
    private var rotateAnimation2: Animation
    private var rotateAnimation3: Animation
    private var rotateAnimation4: Animation

    private var valueAnimator: ValueAnimator = ValueAnimator()

    init {
        LayoutInflater.from(context).inflate(R.layout.view_defaultheaderview, this)

        var params = LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 0)
        params.gravity = Gravity.CENTER
        layoutParams = params

        rotateAnimation1 = RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        rotateAnimation1.duration = 1000
        rotateAnimation1.repeatCount = INFINITE
        rotateAnimation1.interpolator = LinearInterpolator()

        rotateAnimation2 = RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        rotateAnimation2.duration = 1000
        rotateAnimation2.repeatCount = INFINITE
        rotateAnimation2.interpolator = AccelerateDecelerateInterpolator()

        rotateAnimation3 = RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        rotateAnimation3.duration = 1000
        rotateAnimation3.repeatCount = INFINITE
        rotateAnimation3.interpolator = AccelerateInterpolator()

        rotateAnimation4 = RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        rotateAnimation4.duration = 1000
        rotateAnimation4.repeatCount = INFINITE
        rotateAnimation4.interpolator = AccelerateInterpolator()

    }

    override fun setVisibleHeight(height: Float) {
        var params = layoutParams
        params.height = height.toInt()
        layoutParams = params
    }

    override fun isEnoughtToRefresh(): Boolean {
        var currentHeight = layoutParams.height
        return currentHeight >= headerHeight / 2
    }

    override fun startRefresh() {
        changeHeight(layoutParams.height, headerHeight, {}, { startRotate() })
    }

    override fun endRefresh() {
        changeHeight(layoutParams.height, 0, { stopRotate() }, {})
    }

    override fun cancelRefresh() {
        changeHeight(layoutParams.height, 0, {}, {})
    }

    private fun changeHeight(currentHeight: Int, target: Int, start: () -> Unit, end: () -> Unit) {
        if (valueAnimator.isRunning) {
            valueAnimator.cancel()
        }
        valueAnimator = ValueAnimator.ofInt(currentHeight, target)
        valueAnimator.duration = 500
        valueAnimator.addUpdateListener {
            var params = layoutParams
            params.height = valueAnimator.animatedValue as Int
            layoutParams = params
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
        stopRotate()
        iv1.startAnimation(rotateAnimation1)
        iv2.postDelayed({ iv2.startAnimation(rotateAnimation2) }, 800)
        iv3.postDelayed({ iv3.startAnimation(rotateAnimation3) }, 500)
        iv4.postDelayed({ iv4.startAnimation(rotateAnimation4) }, 100)
    }

    private fun stopRotate() {
        rotateAnimation1.cancel()
        rotateAnimation2.cancel()
        rotateAnimation3.cancel()
        rotateAnimation4.cancel()
    }

}