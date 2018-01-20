package com.ljf.eyepetizer.fragment

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ljf.eyepetizer.R
import com.ljf.eyepetizer.adapter.HomeCategoryAdapter
import com.ljf.eyepetizer.adapter.HomeFragmentAdapter
import com.ljf.eyepetizer.manager.CategoryManager
import com.ljf.eyepetizer.model.Category
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Created by mr.lin on 2017/12/13.
 * 首页fragment
 */
class HomeFragment : BaseFragment() {

    private lateinit var guideAdapter: HomeCategoryAdapter
    private lateinit var fragmentAdapter: HomeFragmentAdapter

    private var categories = ArrayList<Category>()
    private var fragments = ArrayList<BaseFragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        initData()
        initAdapter()

    }

    private fun init() {
        CategoryManager.addOnGetCategoryListener(object : CategoryManager.OnGetCategoryListener {
            override fun onGetCatefoty(categories: List<Category>) {
                initData()
            }
        })
    }

    private fun initData() {
        categories.addAll(CategoryManager.categories)
        for (category in categories) {
            var fragment = ContentFragment()
            var bundle = Bundle()
            bundle.putSerializable(ContentFragment.CATEGORY, category)
            fragment.arguments = bundle
            fragments.add(fragment)
        }
    }

    private fun initAdapter() {
        guideAdapter = HomeCategoryAdapter(context, categories)
        guideAdapter.onSelectPositionChangeListener = object : HomeCategoryAdapter.OnSelectPositionChangeListener {
            override fun onSelectPositionChange(position: Int) {
                viewPager.currentItem = position
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
        viewPager.currentItem = 1

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                guideAdapter.selectPostition(position)
            }
        })
    }

}