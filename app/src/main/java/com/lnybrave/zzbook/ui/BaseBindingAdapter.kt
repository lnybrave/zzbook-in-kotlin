package com.lnybrave.zzbook.ui

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView


abstract class BaseBindingAdapter<B : ViewDataBinding> : RecyclerView.Adapter<BaseBindingViewHolder<B>>() {

    var mListener: ((pos: Int) -> Unit)? = null

    override fun onBindViewHolder(holder: BaseBindingViewHolder<B>, position: Int) {
        holder.binding.root.setOnClickListener {
            mListener?.invoke(holder.adapterPosition)
        }
    }

    fun setOnItemClickListener(listener: ((pos: Int) -> Unit)) {
        mListener = listener
    }

}

class BaseBindingViewHolder<out T : ViewDataBinding>(val binding: T) : RecyclerView.ViewHolder(binding.root)