package com.ljf.eyepetizer.views

import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.HorizontalScrollView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.ljf.eyepetizer.R

/**
 * Created by mr.lin on 2018/1/31.
 * 与ViewPager完美结合的顺滑引导条 https://www.jianshu.com/p/affbf7d8f646
 */
class GuideSlideView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : HorizontalScrollView(context, attrs, defStyleAttr) {

    private val screenWidth: Int

    private lateinit var linearLayout: LinearLayout
    private lateinit var lineView: View

    private lateinit var viewPager: ViewPager
    private var viewPagerState = ViewPager.SCROLL_STATE_IDLE
    private var lastX: Float = 0f

    private var datas = ArrayList<String>()

    private var minMarginLeft = 0
    private var isFirstSelect = true

    var onGuideSlideItemClickListener: OnGuideSlideItemClickListener? = null

    private var currentPosition: Int = 0

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    init {
        initView()
        val windowManager: WindowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        var point = Point()
        windowManager.defaultDisplay.getSize(point)
        screenWidth = point.x
    }

    private fun initView() {
        var rootLinearLayout = RelativeLayout(context)
        addView(rootLinearLayout)

        linearLayout = LinearLayout(context)
        linearLayout.orientation = LinearLayout.HORIZONTAL
        rootLinearLayout.addView(linearLayout)

        lineView = LayoutInflater.from(context).inflate(R.layout.view_lineview, rootLinearLayout, false)
        var params = lineView.layoutParams as RelativeLayout.LayoutParams
        params.leftMargin = 7
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
        rootLinearLayout.addView(lineView, params)

        setOnTouchListener { _, event ->
            if (viewPagerState == ViewPager.SCROLL_STATE_DRAGGING) {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> {
                        lastX = event.x
                    }

                    MotionEvent.ACTION_MOVE -> {
                        if (lastX == 0f) {
                            lastX = event.x
                        } else {
                            viewPager.scrollBy(-Math.round(event.x - lastX), 0)
                            lastX = event.x
                        }
                    }

                    MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                        lastX = 0f
                    }

                }
                true
            }
            false
        }
    }

    fun setDatas(strings: List<String>) {
        datas.clear()
        datas.addAll(strings)
        notifyDataSetChange()
    }

    private fun notifyDataSetChange() {
        linearLayout.removeAllViews()
        for (i in 0 until datas.size) {
            var viewHolder = ViewHolder(LayoutInflater.from(context).inflate(R.layout.viewholder_guide, linearLayout, false))
            viewHolder.view.setOnClickListener { setSelect(viewHolder.view.tag as Int) }
            viewHolder.textView.text = datas[i]
            viewHolder.view.tag = i
            linearLayout.addView(viewHolder.view)
        }
        minMarginLeft = 0
        isFirstSelect = true
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        if (minMarginLeft == 0 && linearLayout.childCount > 0) {
            val view = linearLayout.getChildAt(0)
            minMarginLeft = (view.width - lineView.width) / 2
        }
    }

    fun setViewPager(viewPager: ViewPager) {
        this.viewPager = viewPager
        viewPager.addOnPageChangeListener(getOnPageChangeListener())
    }

    private fun getOnPageChangeListener(): ViewPager.OnPageChangeListener {
        return object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
                viewPagerState = state
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    setSelect(viewPager.currentItem)
                }
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                if (linearLayout.childCount == 0) {
                    return
                }
                setPosition(position, positionOffset)
            }

            override fun onPageSelected(position: Int) {
                if (isFirstSelect) {
                    setSelect(position)
                    isFirstSelect = false
                }
            }
        }
    }

    private fun setSelect(position: Int) {
        onGuideSlideItemClickListener?.onItemClick(position)

        var viewHolder = ViewHolder(linearLayout.getChildAt(currentPosition))
        viewHolder.setChecked(false)

        viewHolder = ViewHolder(linearLayout.getChildAt(position))
        viewHolder.setChecked(true)

        currentPosition = position
    }

    private fun setPosition(position: Int, positionOffset: Float) {
        val currentView = linearLayout.getChildAt(position)

        var targetPosition = position + 1
        if (targetPosition > linearLayout.childCount - 1) {
            targetPosition = linearLayout.childCount - 1
        }
        val targetView = linearLayout.getChildAt(targetPosition)

        val totalLength = (currentView.width + targetView.width) / 2

        val originalX = currentView.left
        val offset = Math.round(totalLength * positionOffset)

        val marginLeft = originalX + offset

        val params = lineView.layoutParams as RelativeLayout.LayoutParams
        params.leftMargin = minMarginLeft + marginLeft
        lineView.layoutParams = params

        scrollX = (marginLeft - screenWidth / 2 + currentView.width + lineView.width)

    }

    class ViewHolder(var view: View) {
        var textView: TextView

        init {
            textView = view.findViewById(R.id.nameTv)
        }

        fun setChecked(checked: Boolean) {
            if (checked) {
                textView.setTextColor(Color.parseColor("#444444"))
            } else {
                textView.setTextColor(Color.parseColor("#888888"))
            }
        }
    }

    interface OnGuideSlideItemClickListener {
        fun onItemClick(position: Int)
    }

}