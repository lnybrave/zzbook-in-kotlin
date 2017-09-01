package com.lnybrave.zzbook.ui.multitype

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.lnybrave.zzbook.R
import com.lnybrave.zzbook.bean.SearchWord
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import kotlinx.android.synthetic.main.item_search_tag.view.*
import me.drakeet.multitype.ItemViewBinder

/**
 * Created by lny on 2017/7/29.
 */
class SearchTagViewBinder : ItemViewBinder<SearchTag, SearchTagViewBinder.ViewHolder>() {

    var onTagClickListener: OnTagClickListener? = null

    override fun onCreateViewHolder(
            inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        val root = inflater.inflate(R.layout.item_search_tag, parent, false)
        return ViewHolder(root)
    }

    override fun onBindViewHolder(holder: ViewHolder, searchTag: SearchTag) {
        holder.update(searchTag.list)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val words: ArrayList<SearchWord> = ArrayList()

        init {
            itemView.tag_layout.adapter = object : TagAdapter<SearchWord>(words) {
                override fun getView(parent: FlowLayout, position: Int, s: SearchWord): View {
                    val tv = LayoutInflater.from(parent.context).inflate(R.layout.item_search_word, itemView.tag_layout, false) as TextView
                    tv.text = s.word
                    return tv
                }
            }

            itemView.tag_layout.setOnTagClickListener { _, position, _ ->
                if (onTagClickListener != null) {
                    onTagClickListener!!.onTagClick(words[position])
                }
                true
            }
        }

        fun update(words: List<SearchWord>) {
            this.words.clear()
            this.words.addAll(words)
            itemView.tag_layout.adapter.notifyDataChanged()
        }
    }

    interface OnTagClickListener {
        fun onTagClick(word: SearchWord)
    }
}