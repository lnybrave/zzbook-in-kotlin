package com.lnybrave.zzbook.ui.multitype

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lnybrave.zzbook.R
import com.lnybrave.zzbook.bean.SearchWord
import kotlinx.android.synthetic.main.item_search_suggest.view.*
import me.drakeet.multitype.ItemViewBinder

/**
 * Created by lny on 2017/7/29.
 */
class SearchSuggestViewBinder : ItemViewBinder<SearchWord, SearchSuggestViewBinder.ViewHolder>() {

    var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(
            inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        val root = inflater.inflate(R.layout.item_search_suggest, parent, false)
        return ViewHolder(root)
    }

    override fun onBindViewHolder(holder: ViewHolder, searchWord: SearchWord) {
        holder.itemView.tvWord.text = searchWord.word
        holder.searchWord = searchWord
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var searchWord: SearchWord

        init {
            itemView.setOnClickListener { v ->
                if (onItemClickListener != null) {
                    onItemClickListener!!.onItemClick(searchWord)
                }
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(word: SearchWord)
    }
}