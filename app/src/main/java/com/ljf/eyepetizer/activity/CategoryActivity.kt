package com.ljf.eyepetizer.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import com.ljf.eyepetizer.R
import com.ljf.eyepetizer.adapter.JsonViewAdapter
import com.ljf.eyepetizer.http.Requester
import com.ljf.eyepetizer.model.FragmentContent
import com.ljf.eyepetizer.model.ViewData
import kotlinx.android.synthetic.main.activity_category.*
import java.util.*

/**
 * Created by mr.lin on 2018/2/6.
 * 分类排序
 */
class CategoryActivity : BaseActivity() {

    var viewDatas = ArrayList<ViewData>()
    lateinit var adapter: JsonViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        init()
    }

    fun init() {
        getDatas("http://baobab.kaiyanapp.com/api/v5/category/list")
        adapter = JsonViewAdapter(this, viewDatas)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        actionbar.setTitle(R.string.category_list)

        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?): Int {
                val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
                val swipeFlags = 0
                return makeMovementFlags(dragFlags, swipeFlags)
            }

            override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                adapter.notifyItemMoved(viewHolder.adapterPosition, target.adapterPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
            }

            override fun isItemViewSwipeEnabled(): Boolean {
                return false
            }

            override fun isLongPressDragEnabled(): Boolean {
                return true
            }
        })

        itemTouchHelper.attachToRecyclerView(recyclerView)

    }

    private fun getDatas(url: String) {
        Requester.getFragmentContent(url, object : Requester.OnResultListener<FragmentContent> {
            override fun onResult(data: FragmentContent?) {
                if (data != null) {
                    viewDatas.addAll(data.itemList)
                    adapter.notifyDataSetChanged()
                }
            }
        })
    }

}