package com.lnybrave.zzbook.ui.multitype

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lnybrave.zzbook.R
import com.lnybrave.zzbook.bean.Book
import com.lnybrave.zzbook.ui.activity.BookDetailActivity
import com.lnybrave.zzbook.utils.ImageUtils
import kotlinx.android.synthetic.main.item_book_complex.view.*
import me.drakeet.multitype.ItemViewBinder

/**
 * Created by lny on 2017/7/29.
 */
class BookComplexViewBinder : ItemViewBinder<Book, BookComplexViewBinder.ViewHolder>() {

    override fun onCreateViewHolder(
            inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        val root = inflater.inflate(R.layout.item_book_complex, parent, false)
        return ViewHolder(root)
    }

    override fun onBindViewHolder(holder: ViewHolder, book: Book) {
        holder.itemView.tvName.text = book.name
        holder.itemView.tvDesc.text = book.desc
        holder.itemView.tvChapterCount.text = "${book.chapterSize}章"
        holder.itemView.tvCatalog.text = book.wordSize
        ImageUtils.displayBookCover(book.coverUrl, holder.itemView.ivCover)
        holder.book = book
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var book: Book

        init {
            itemView.setOnClickListener { v ->
                val intent = Intent(v.context, BookDetailActivity::class.java)
                intent.putExtra("book", book)
                v.context.startActivity(intent)
            }
        }
    }
}