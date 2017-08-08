package com.lnybrave.zzbook.ui.multitype

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lnybrave.zzbook.R
import com.lnybrave.zzbook.bean.Ranking
import kotlinx.android.synthetic.main.item_classification_first.view.*
import me.drakeet.multitype.ItemViewBinder

/**
 * Created by lny on 2017/7/29.
 */
class RankingTitleViewBinder : ItemViewBinder<Ranking, RankingTitleViewBinder.ViewHolder>() {

    override fun onCreateViewHolder(
            inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        val root = inflater.inflate(R.layout.item_classification_first, parent, false)
        return ViewHolder(root)
    }

    override fun onBindViewHolder(holder: ViewHolder, ranking: Ranking) {
        holder.itemView.tvName.text = ranking.name
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}