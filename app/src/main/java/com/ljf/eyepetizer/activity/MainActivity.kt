package com.ljf.eyepetizer.activity

import android.os.Bundle
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout
import com.ljf.eyepetizer.BaseActivity
import com.ljf.eyepetizer.BaseFragment
import com.ljf.eyepetizer.R
import com.ljf.eyepetizer.fragment.FindFragment
import com.ljf.eyepetizer.fragment.HomeFragment
import com.ljf.eyepetizer.fragment.MineFragment
import com.ljf.eyepetizer.fragment.NotifyFragment
import com.ljf.eyepetizer.views.TabItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private lateinit var homeTab: TabItem<HomeFragment>
    private lateinit var findTab: TabItem<FindFragment>
    private lateinit var notifyTab: TabItem<NotifyFragment>
    private lateinit var mineTab: TabItem<MineFragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        val params: LinearLayout.LayoutParams? = LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
        params?.weight = 1f
        homeTab = TabItem(this, HomeFragment::class.java)
        findTab = TabItem(this, FindFragment::class.java)
        notifyTab = TabItem(this, NotifyFragment::class.java)
        mineTab = TabItem(this, MineFragment::class.java)
        bt_ll.addView(homeTab, params)
        bt_ll.addView(findTab, params)
        bt_ll.addView(notifyTab, params)
        bt_ll.addView(mineTab, params)

        homeTab.setOnClickListener(onTabItemClickListener)
        findTab.setOnClickListener(onTabItemClickListener)
        notifyTab.setOnClickListener(onTabItemClickListener)
        mineTab.setOnClickListener(onTabItemClickListener)

    }

    private var onTabItemClickListener = View.OnClickListener { v ->
        showFragment(v.tag as Class<BaseFragment>)
    }

    private fun showFragment(fragmentClass: Class<BaseFragment>?) {
        val fragment = supportFragmentManager.findFragmentByTag(fragmentClass?.simpleName) ?: fragmentClass?.newInstance()
        val trasaction = supportFragmentManager.beginTransaction()
        trasaction.replace(R.id.frame_layout, fragment)
        trasaction.commit()
    }


}
