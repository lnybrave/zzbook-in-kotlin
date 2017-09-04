package com.lnybrave.zzbook.ui.multitype

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lnybrave.zzbook.R
import kotlinx.android.synthetic.main.item_search_title.view.*
import me.drakeet.multitype.ItemViewBinder

/**
 * Created by lny on 2017/7/29.
 */
class SearchTitleViewBinder : ItemViewBinder<SearchTitle, SearchTitleViewBinder.ViewHolder>() {

    override fun onCreateViewHolder(
            inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        val root = inflater.inflate(R.layout.item_search_title, parent, false)
        return ViewHolder(root)
    }

    override fun onBindViewHolder(holder: ViewHolder, searchTitle: SearchTitle) {
        holder.itemView.tvTitle.text = searchTitle.title
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}