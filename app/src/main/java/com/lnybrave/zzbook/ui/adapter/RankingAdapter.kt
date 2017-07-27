package com.lnybrave.zzbook.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.lnybrave.zzbook.bean.Ranking
import com.lnybrave.zzbook.bean.Topic
import com.lnybrave.zzbook.databinding.ItemRankingBinding
import com.lnybrave.zzbook.databinding.ItemRecommendationBinding


class RankingAdapter(private val mList: List<Ranking>) : BaseBindingAdapter<ItemRankingBinding>() {

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: DataBoundViewHolder<ItemRankingBinding>, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.binding.ranking = mList[position]
        holder.binding.executePendingBindings()
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): DataBoundViewHolder<ItemRankingBinding> {
        return DataBoundViewHolder(ItemRankingBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
}