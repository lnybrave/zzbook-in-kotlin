package com.lnybrave.zzbook.ui.multitype

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lnybrave.zzbook.R
import com.lnybrave.zzbook.bean.Topic
import kotlinx.android.synthetic.main.item_topic_title.view.*
import me.drakeet.multitype.ItemViewBinder

/**
 * Created by lny on 2017/7/29.
 */
class TopicTitleViewBinder : ItemViewBinder<Topic, TopicTitleViewBinder.ViewHolder>() {

    override fun onCreateViewHolder(
            inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        val root = inflater.inflate(R.layout.item_topic_title, parent, false)
        return ViewHolder(root)
    }

    override fun onBindViewHolder(holder: ViewHolder, topic: Topic) {
        holder.itemView.tvTitle.text = topic.name
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}