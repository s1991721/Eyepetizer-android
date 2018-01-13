package com.ljf.eyepetizer.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.ViewGroup
import com.ljf.eyepetizer.model.ViewData
import com.ljf.eyepetizer.utils.JsonViewUtils
import com.ljf.eyepetizer.views.jsonview.BaseJsonView

/**
 * Created by mr.lin on 2018/1/13.
 * 动态布局adapter
 */
class JsonViewAdapter(var context: Context, var datas: List<ViewData>) : Adapter<JsonViewAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        JsonViewUtils.injectView(holder.itemView as BaseJsonView, datas[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(JsonViewUtils.viewTypeGetView(context, viewType))
    }

    override fun getItemViewType(position: Int): Int {
        return JsonViewUtils.viewDataGetType(datas[position])
    }

    class ViewHolder(itemView: BaseJsonView?) : RecyclerView.ViewHolder(itemView)

}