package com.lnybrave.zzbook.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.lnybrave.zzbook.bean.Book
import com.lnybrave.zzbook.databinding.ItemBookshelfBinding
import com.lnybrave.zzbook.ui.BaseBindingAdapter
import com.lnybrave.zzbook.ui.BaseBindingViewHolder


class BookshelfAdapter(private val mList: List<Book>) : BaseBindingAdapter<ItemBookshelfBinding>() {

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: BaseBindingViewHolder<ItemBookshelfBinding>, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.binding.book = mList[position]
        holder.binding.executePendingBindings()
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): BaseBindingViewHolder<ItemBookshelfBinding> {
        return BaseBindingViewHolder(ItemBookshelfBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
}