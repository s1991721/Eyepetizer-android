package com.ljf.eyepetizer.views

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.RelativeLayout
import com.ljf.eyepetizer.R

/**
 * Created by mr.lin on 2017/12/24.
 * 动态三角
 */
class MotionalArrowView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : RelativeLayout(context, attrs, defStyleAttr) {

    lateinit var upImageView: ImageView
    lateinit var downImageView: ImageView

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)

    init {
        initView()
    }

    fun initView() {
        upImageView = ImageView(context)
        downImageView = ImageView(context)

        upImageView.setImageDrawable(ContextCompat.getDrawable(context, R.mipmap.ic_action_up))
        downImageView.setImageDrawable(ContextCompat.getDrawable(context, R.mipmap.ic_action_up))

        //如果是正方形，则看不出效果，因为图片太大了
        var params = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        params.addRule(ALIGN_PARENT_BOTTOM)

        addView(upImageView)
        addView(downImageView, params)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (!isInEditMode) {
            showAnimation()
        }
    }

    fun showAnimation() {
        var upAnimator = ObjectAnimator.ofFloat(upImageView, "alpha", 0.3f, 1f, 0.3f)
        var downAnimator = ObjectAnimator.ofFloat(downImageView, "alpha", 0.3f, 1f, 0.3f)
        upAnimator.duration = 1000
        downAnimator.duration = 1000
        upAnimator.startDelay = 500

        var animatorSet = AnimatorSet()
        animatorSet.playTogether(upAnimator, downAnimator)
        animatorSet.addListener(object : Animator.AnimatorListener {

            override fun onAnimationEnd(animation: Animator?) {
                animatorSet.startDelay = 500
                animatorSet.start()
            }

            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {
            }
        })
        animatorSet.start()
    }

}