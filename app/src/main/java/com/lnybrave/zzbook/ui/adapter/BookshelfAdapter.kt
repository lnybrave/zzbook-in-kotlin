package com.lnybrave.zzbook.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.lnybrave.zzbook.bean.Book
import com.lnybrave.zzbook.databinding.ItemBookshelfBinding
import com.lnybrave.zzbook.utils.loadBookCover
import kotlinx.android.synthetic.main.item_bookshelf.view.*


class BookshelfAdapter(private val mList: List<Book>) : BaseBindingAdapter<ItemBookshelfBinding>() {

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: DataBoundViewHolder<ItemBookshelfBinding>, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.binding.book = mList[position]
        holder.binding.executePendingBindings()
        loadBookCover(holder.binding.book.coverUrl, holder.itemView.ivCover)
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): DataBoundViewHolder<ItemBookshelfBinding> {
        return DataBoundViewHolder(ItemBookshelfBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
}