package com.ljf.eyepetizer.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.ljf.eyepetizer.fragment.BaseFragment

/**
 * Created by mr.lin on 2018/1/19.
 * 首页各栏目适配器
 */
class HomeFragmentAdapter(var fragments: List<BaseFragment>, fm: FragmentManager?) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return fragments.get(position)
    }

    override fun getCount(): Int {
        return fragments.size
    }

}
