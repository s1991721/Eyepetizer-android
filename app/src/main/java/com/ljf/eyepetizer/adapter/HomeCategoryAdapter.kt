package com.ljf.eyepetizer.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ljf.eyepetizer.R
import com.ljf.eyepetizer.model.Category

/**
 * Created by mr.lin on 2018/1/19.
 * 首页顶部分类适配器
 */
class HomeCategoryAdapter(var context: Context, var datas: List<Category>) : RecyclerView.Adapter<HomeCategoryAdapter.ViewHolder>() {

    private var selectPosition = 1
    var onSelectPositionChangeListener: OnSelectPositionChangeListener? = null

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameTv.text = datas[position].name
        if (selectPosition == position) {
            holder.nameTv.setTextColor(ContextCompat.getColor(context, R.color.c444444))
            holder.lineView.visibility = View.VISIBLE
        } else {
            holder.lineView.visibility = View.INVISIBLE
            holder.nameTv.setTextColor(ContextCompat.getColor(context, R.color.c888888))
        }
        holder.itemView.setOnClickListener {
            onSelectPositionChangeListener?.onSelectPositionChange(position)
            selectPostition(position)
        }
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.viewholder_home_guide_category, parent, false)
        return ViewHolder(view)
    }

    fun selectPostition(position: Int) {
        selectPosition = position
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nameTv: TextView = itemView.findViewById(R.id.nameTv)
        var lineView: View = itemView.findViewById(R.id.lineView)
    }

    interface OnSelectPositionChangeListener {
        fun onSelectPositionChange(position: Int)
    }
}