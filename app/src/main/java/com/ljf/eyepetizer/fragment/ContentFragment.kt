package com.ljf.eyepetizer.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ljf.eyepetizer.R
import com.ljf.eyepetizer.adapter.JsonViewAdapter
import com.ljf.eyepetizer.http.Requester
import com.ljf.eyepetizer.model.Category
import com.ljf.eyepetizer.model.FragmentContent
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

    private lateinit var fragmentContent: FragmentContent

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
                viewDatas.clear()
                getDatas(category.apiUrl)
            }
        }
        recyclerView.onLoadMoreListener = object : RefreshRecyclerView.OnLoadMoreListener {
            override fun onLoadMore() {
                recyclerView.postDelayed({ getDatas(fragmentContent.nextPageUrl) }, 3000)
            }
        }
        if (viewDatas.size == 0) {
            recyclerView.startRefresh()
        }
    }

    fun getDatas(url: String) {
        Requester.getFragmentContent(url, object : Requester.OnResultListener<FragmentContent> {
            override fun onResult(data: FragmentContent?) {
                if (data != null) {
                    fragmentContent = data.copy()
                    showData()
                }
            }
        })
    }

    fun showData() {
        viewDatas.addAll(fragmentContent.itemList)

        if (fragmentContent.nextPageUrl == "null") {
            viewDatas.add(ViewData("end"))
            recyclerView?.loadEnable = false
        }

        adapter.notifyDataSetChanged()
        recyclerView?.stopRefresh()
    }

}