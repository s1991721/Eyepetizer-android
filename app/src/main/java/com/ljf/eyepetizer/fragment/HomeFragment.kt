package com.ljf.eyepetizer.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.gson.reflect.TypeToken
import com.ljf.eyepetizer.R
import com.ljf.eyepetizer.adapter.HomeCategoryAdapter
import com.ljf.eyepetizer.adapter.HomeFragmentAdapter
import com.ljf.eyepetizer.model.Category
import com.ljf.eyepetizer.utils.SpUtils
import com.ljf.eyepetizer.utils.SpUtils.Companion.KEY_CATEGORY_LIST
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Created by mr.lin on 2017/12/13.
 * 首页fragment
 */
class HomeFragment : BaseFragment() {

    private lateinit var guideAdapter: HomeCategoryAdapter
    private lateinit var fragmentAdapter: HomeFragmentAdapter

    private var categorys = ArrayList<Category>()
    private var fragments = ArrayList<BaseFragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
        initAdapter()

    }

    private fun initData() {
        var list = SpUtils.getList<List<Category>>(KEY_CATEGORY_LIST, object : TypeToken<List<Category>>() {}.type)
        categorys.addAll(list)

        fragments.add(DiscoveryFragment())
    }

    private fun initAdapter() {
        guideAdapter = HomeCategoryAdapter(context, categorys)
        guideAdapter.onSelectPositionChangeListener = object : HomeCategoryAdapter.OnSelectPositionChangeListener {
            override fun onSelectPositionChange(position: Int) {
                Toast.makeText(context, "" + position, Toast.LENGTH_LONG).show()
            }

        }
        fragmentAdapter = HomeFragmentAdapter(fragments, fragmentManager)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = guideAdapter

        viewPager.adapter = fragmentAdapter
    }

}