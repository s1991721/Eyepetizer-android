package com.ljf.eyepetizer.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ljf.eyepetizer.R
import com.ljf.eyepetizer.adapter.JsonViewAdapter
import com.ljf.eyepetizer.http.Requester
import com.ljf.eyepetizer.model.Category
import com.ljf.eyepetizer.model.ViewData
import com.ljf.eyepetizer.views.refreshview.RefreshRecyclerView
import kotlinx.android.synthetic.main.fragment_content.*

/**
 * Created by mr.lin on 2018/1/20.
 * 内容Fragment
 */
class ContentFragment : BaseFragment() {

    companion object {
        val CATEGORY = "category"
    }

    var viewDatas = ArrayList<ViewData>()
    lateinit var adapter: JsonViewAdapter
    lateinit var category: Category

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        category = arguments.getSerializable(CATEGORY) as Category
        adapter = JsonViewAdapter(context, viewDatas)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_content, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.setLayoutManager(LinearLayoutManager(context))
        recyclerView.setAdapter(adapter)
        recyclerView.onRefreshListener = object : RefreshRecyclerView.OnRefreshListener {
            override fun onRefresh() {
                initData()
            }
        }
        recyclerView.onLoadMoreListener = object : RefreshRecyclerView.OnLoadMoreListener {
            override fun onLoadMore() {
                Toast.makeText(context, "load more", Toast.LENGTH_LONG).show()
                recyclerView.postDelayed({ recyclerView.stopLoadMore() }, 3000)
            }
        }
        if (viewDatas.size == 0) {
            recyclerView.startRefresh()
        }
    }

    fun initData() {
        Requester.getFragmentContent(category, object : Requester.OnResultListener<List<ViewData>> {
            override fun onResult(data: List<ViewData>?) {
                if (data != null) {
                    viewDatas.clear()
                    viewDatas.addAll(data)
                    adapter.notifyDataSetChanged()
                    recyclerView?.stopRefresh()
                }
            }
        })
    }

}