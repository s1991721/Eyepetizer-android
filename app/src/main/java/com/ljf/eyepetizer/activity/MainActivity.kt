package com.ljf.eyepetizer.activity

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout
import com.ljf.eyepetizer.BaseActivity
import com.ljf.eyepetizer.BaseFragment
import com.ljf.eyepetizer.R
import com.ljf.eyepetizer.fragment.NotifyFragment
import com.ljf.eyepetizer.views.TabItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private lateinit var notifyTab: TabItem<NotifyFragment>

    private var currentFragmentTag: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        homeTab.setOnClickListener(onTabItemClickListener)
        findTab.setOnClickListener(onTabItemClickListener)
        hotTab.setOnClickListener(onTabItemClickListener)
        mineTab.setOnClickListener(onTabItemClickListener)

        val params: LinearLayout.LayoutParams? = LinearLayout.LayoutParams(0, MATCH_PARENT)
        params?.weight = 1f
        notifyTab = TabItem(this, NotifyFragment::class.java)
        notifyTab.setIcon(ContextCompat.getDrawable(this, R.mipmap.ic_tab_strip_icon_category_selected), ContextCompat.getDrawable(this, R.mipmap.ic_tab_strip_icon_category))
        notifyTab.setTip("动态添加")
        notifyTab.setOnClickListener(onTabItemClickListener)
        bt_ll.addView(notifyTab, params)

        homeTab.callOnClick()
    }

    private var onTabItemClickListener = View.OnClickListener { v ->
        clearTabItemsState()
        showFragment(Class.forName(v.tag.toString()) as Class<BaseFragment>)
    }

    private fun clearTabItemsState() {
        homeTab.isSelect(false)
        findTab.isSelect(false)
        hotTab.isSelect(false)
        mineTab.isSelect(false)
        notifyTab.isSelect(false)
    }

    private fun showFragment(fragmentClass: Class<BaseFragment>?) {
        val trasaction = supportFragmentManager.beginTransaction()
        val hideFragment = supportFragmentManager.findFragmentByTag(currentFragmentTag)
        if (hideFragment != null) {
            trasaction.hide(hideFragment)
        }

        var showFragment = supportFragmentManager.findFragmentByTag(fragmentClass?.simpleName)

        if (showFragment == null) {
            showFragment = fragmentClass?.newInstance()
            trasaction.add(R.id.frame_layout, showFragment, fragmentClass?.simpleName)
        } else {
            trasaction.show(showFragment)
        }
        trasaction.commit()
        currentFragmentTag = fragmentClass?.simpleName
    }

}
