package com.lnybrave.zzbook.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.lnybrave.zzbook.bean.Classification
import com.lnybrave.zzbook.databinding.ItemClassificationBinding


class ClassificationAdapter(private val mList: List<Classification>) : BaseBindingAdapter<ItemClassificationBinding>() {

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: DataBoundViewHolder<ItemClassificationBinding>, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.binding.classification = mList[position]
        holder.binding.executePendingBindings()
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): DataBoundViewHolder<ItemClassificationBinding> {
        return DataBoundViewHolder(ItemClassificationBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
}