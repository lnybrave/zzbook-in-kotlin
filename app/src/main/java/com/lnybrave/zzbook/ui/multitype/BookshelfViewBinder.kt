package com.lnybrave.zzbook.ui.multitype

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lnybrave.zzbook.R
import com.lnybrave.zzbook.bean.Book
import me.drakeet.multitype.ItemViewBinder

/**
 * Created by lny on 2017/7/28.
 */
class BookshelfViewBinder : ItemViewBinder<Book, BookshelfViewBinder.ViewHolder>() {

    override fun onCreateViewHolder(
            inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        val root = inflater.inflate(R.layout.item_bookshelf, parent, false)
        return ViewHolder(root)
    }

    override fun onBindViewHolder(holder: ViewHolder, book: Book) {

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}