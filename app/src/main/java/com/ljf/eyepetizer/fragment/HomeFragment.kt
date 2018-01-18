package com.ljf.eyepetizer.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ljf.eyepetizer.BaseFragment
import com.ljf.eyepetizer.R
import com.ljf.eyepetizer.adapter.JsonViewAdapter
import com.ljf.eyepetizer.http.Requester
import com.ljf.eyepetizer.model.ViewData
import com.ljf.eyepetizer.views.refreshview.RefreshRecyclerView.OnLoadMoreListener
import com.ljf.eyepetizer.views.refreshview.RefreshRecyclerView.OnRefreshListener
import kotlinx.android.synthetic.main.fragment_home.*
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by mr.lin on 2017/12/13.
 * 首页fragment
 */
class HomeFragment : BaseFragment() {

    var viewDatas = ArrayList<ViewData>()
    lateinit var adapter: JsonViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = JsonViewAdapter(context, viewDatas)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater?.inflate(R.layout.fragment_home, container, false)
        //NullPointerException 此时控件没有初始化
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.setLayoutManager(LinearLayoutManager(context))
        recyclerView.setAdapter(adapter)
        recyclerView.onRefreshListener = object : OnRefreshListener {
            override fun onRefresh() {
                initData()
            }
        }
        recyclerView.onLoadMoreListener = object : OnLoadMoreListener {
            override fun onLoadMore() {
                Toast.makeText(context, "load more", Toast.LENGTH_LONG).show()
                recyclerView.postDelayed({ recyclerView.stopLoadMore() }, 3000)
            }
        }
        recyclerView.startRefresh()
    }

    fun initData() {
        Requester.apiService().getTabDiscovery().enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>) {
                toModel(response.body()!!.string())
                adapter.notifyDataSetChanged()
                if (recyclerView != null) {
                    recyclerView.stopRefresh()
                }
            }

            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
            }
        })
    }

    fun toModel(string: String) {
        var json = JSONObject(string)
        var jsonArray = json.getJSONArray("itemList")
        viewDatas.clear()
        for (i in 0 until jsonArray.length()) {
            var viewData = ViewData(jsonArray.getJSONObject(i))
            viewDatas.add(viewData)
        }
    }

}