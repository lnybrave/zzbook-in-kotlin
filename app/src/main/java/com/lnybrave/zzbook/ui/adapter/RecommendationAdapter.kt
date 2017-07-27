package com.lnybrave.zzbook.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.lnybrave.zzbook.bean.Topic
import com.lnybrave.zzbook.databinding.ItemRecommendationBinding


class RecommendationAdapter(private val mList: List<Topic>) : BaseBindingAdapter<ItemRecommendationBinding>() {

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: DataBoundViewHolder<ItemRecommendationBinding>, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.binding.topic = mList[position]
        holder.binding.executePendingBindings()
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): DataBoundViewHolder<ItemRecommendationBinding> {
        return DataBoundViewHolder(ItemRecommendationBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
}