package com.ljf.eyepetizer.views.refreshview

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.LinearLayout
import com.ljf.eyepetizer.utils.Logger

/**
 * Created by mr.lin on 2018/1/15.
 * 刷新控件
 */
class RefreshRecyclerView(context: Context, attrs: AttributeSet?, defStyle: Int) : LinearLayout(context, attrs, defStyle) {

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)

    private var headerView: HeaderView
    private var recyclerView = RecyclerView(context)
    private var footerView: FooterView

    var delay = 2000L

    companion object {
        val STATE_NORMAL = 0
        val STATE_PULLING = 1
        val STATE_REFRESHING = 2
        val STATE_LOADING = 3
    }

    var currentState = STATE_NORMAL

    init {

        orientation = VERTICAL

        headerView = DefaultHeaderView(context)
        footerView = DefaultFooterView(context)

        addView(headerView)
        addView(recyclerView, 1)
        addView(footerView)

    }

    var startY = 0f

    var onRefreshListener: OnRefreshListener? = null
    var onLoadMoreListener: OnLoadMoreListener? = null

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (isScrollToTop()) {

            Logger.i("RefreshRecyclerView", "ScrollToTop")
            when (ev.action) {

                MotionEvent.ACTION_DOWN -> {
                    startY = ev.y
                }

                MotionEvent.ACTION_MOVE -> {
                    if (currentState != STATE_REFRESHING) {
                        if (ev.y - startY > 0) {
                            changeState(STATE_PULLING)
                            headerView.setVisibleHeight(ev.y - startY)
                            return true
                        }
                        changeState(STATE_NORMAL)
                    }
                }

                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    if (currentState == STATE_PULLING) {
                        var toState = STATE_NORMAL
                        if (headerView.isEnoughtToRefresh()) {
                            toState = STATE_REFRESHING
                        }
                        changeState(toState)
                    }
                }

            }
        }

        if (isScrollToBottom()) {

            Logger.i("RefreshRecyclerView", "ScrollToBottom")
            changeState(STATE_LOADING)

        }

        return super.dispatchTouchEvent(ev)
    }

    //刷新操作
    private fun isScrollToTop(): Boolean {
        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
        val position = layoutManager.findFirstVisibleItemPosition()
        if (position != 0) {
            return false
        }
        val firstVisiableChildView = layoutManager.findViewByPosition(position)
        return firstVisiableChildView.top == 0
    }

    private fun changeState(state: Int) {
        when (currentState) {
            STATE_NORMAL -> {
                if (state == STATE_REFRESHING) {
                    notifyRefresh()
                }
                if (state == STATE_LOADING) {
                    notifyLoadMore()
                }
                currentState = state
            }
            STATE_PULLING -> {
                if (state == STATE_REFRESHING) {
                    notifyRefresh()
                }
                if (state == STATE_NORMAL) {
                    notifyRefreshCancel()
                }
                currentState = state
            }
            STATE_REFRESHING -> {
                if (state == STATE_NORMAL) {
                    notifyRefreshEnd()
                }
                currentState = state
            }
            STATE_LOADING -> {
                if (state == STATE_NORMAL) {
                    notifyLoadMoreEnd()
                }
                currentState = state
            }

        }
    }

    private fun notifyRefresh() {
        postDelayed({ onRefreshListener?.onRefresh() }, delay)
        headerView.startRefresh()
    }

    private fun notifyRefreshEnd() {
        headerView.endRefresh()
    }

    private fun notifyRefreshCancel() {
        headerView.cancelRefresh()
    }

    //更多操作
    fun isScrollToBottom(): Boolean {
        return recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset() >= recyclerView.computeVerticalScrollRange()
    }

    fun notifyLoadMore() {
        onLoadMoreListener?.onLoadMore()
    }

    fun notifyLoadMoreEnd() {

    }

    //对外使用
    fun setLayoutManager(layout: RecyclerView.LayoutManager) {
        recyclerView.layoutManager = layout
    }

    fun setAdapter(adapter: RecyclerView.Adapter<*>) {
        recyclerView.adapter = adapter
    }

    fun setHeaderView(view: HeaderView) {
        headerView = view
        invalidate()
    }

    fun setFooterView(view: FooterView) {
        footerView = view
        invalidate()
    }

    fun startRefresh() {
        changeState(STATE_REFRESHING)
    }

    fun stopRefresh() {
        changeState(STATE_NORMAL)
    }

    fun stopLoadMore() {
        changeState(STATE_NORMAL)
    }

    interface OnRefreshListener {
        fun onRefresh()
    }

    interface OnLoadMoreListener {
        fun onLoadMore()
    }

}