package com.ljf.eyepetizer.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ljf.eyepetizer.R
import com.ljf.eyepetizer.activity.CategoryActivity
import com.ljf.eyepetizer.adapter.HomeFragmentAdapter
import com.ljf.eyepetizer.manager.CategoryManager
import com.ljf.eyepetizer.model.Category
import com.ljf.eyepetizer.views.GuideSlideView
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Created by mr.lin on 2017/12/13.
 * 首页fragment
 */
class HomeFragment : BaseFragment() {

    private lateinit var fragmentAdapter: HomeFragmentAdapter

    private var categories = ArrayList<Category>()
    private var fragments = ArrayList<BaseFragment>()
    private var indexStrings = ArrayList<String>()

    private val onGetCategoryListener = object : CategoryManager.OnGetCategoryListener {
        override fun onGetCatefoty(categories: List<Category>) {
            initData()
            fragmentAdapter.notifyDataSetChanged()
            guideslideview.setDatas(indexStrings)
        }
    }
    private val onGuideSlideItemClickListener = object : GuideSlideView.OnGuideSlideItemClickListener {
        override fun onItemClick(position: Int) {
            viewPager.setCurrentItem(position, false)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
        initAdapter()
    }

    private fun initData() {
        categories.addAll(CategoryManager.categories)
        for (category in categories) {
            val fragment = ContentFragment()
            val bundle = Bundle()
            bundle.putSerializable(ContentFragment.CATEGORY, category)
            fragment.arguments = bundle
            fragments.add(fragment)

            indexStrings.add(category.name)
        }
    }

    private fun initAdapter() {
        fragmentAdapter = HomeFragmentAdapter(fragments, fragmentManager)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        CategoryManager.addOnGetCategoryListener(onGetCategoryListener)
        return inflater?.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager.adapter = fragmentAdapter

        guideslideview.setViewPager(viewPager)
        guideslideview.setDatas(indexStrings)
        guideslideview.onGuideSlideItemClickListener = onGuideSlideItemClickListener

        viewPager.currentItem = 1
        viewPager.offscreenPageLimit=3

        categoryIv.setOnClickListener { startActivity(Intent(context, CategoryActivity::class.java)) }
    }

    override fun onDestroyView() {
        CategoryManager.removeOnGetCategoryListener(onGetCategoryListener)
        super.onDestroyView()
    }

}