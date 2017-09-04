package com.lnybrave.zzbook.ui.multitype

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lnybrave.zzbook.R
import kotlinx.android.synthetic.main.item_topic_title.view.*
import me.drakeet.multitype.ItemViewBinder

/**
 * Created by lny on 2017/7/29.
 */
class SearchCountViewBinder : ItemViewBinder<Integer, SearchCountViewBinder.ViewHolder>() {

    override fun onCreateViewHolder(
            inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        val root = inflater.inflate(R.layout.item_search_count, parent, false)
        return ViewHolder(root)
    }

    override fun onBindViewHolder(holder: ViewHolder, count: Integer) {
        holder.itemView.tvTitle.text = "共找到${count}条结果"
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}