package com.lnybrave.zzbook.ui.multitype

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.lnybrave.zzbook.R
import com.lnybrave.zzbook.bean.Book
import com.lnybrave.zzbook.ui.activity.CatalogActivity
import com.lnybrave.zzbook.ui.activity.SearchActivity
import com.lnybrave.zzbook.utils.BookUtils
import com.lnybrave.zzbook.utils.ImageUtils
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import kotlinx.android.synthetic.main.item_book_detail.view.*
import me.drakeet.multitype.ItemViewBinder

/**
 * Created by lny on 2017/7/29.
 */
class BookDetailViewBinder : ItemViewBinder<Book, BookDetailViewBinder.ViewHolder>() {

    override fun onCreateViewHolder(
            inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        val root = inflater.inflate(R.layout.item_book_detail, parent, false)
        return ViewHolder(root)
    }

    override fun onBindViewHolder(holder: ViewHolder, book: Book) {
        ImageUtils.displayBookCover(book.coverUrl, holder.itemView.ivCover)
        holder.itemView.tvName.text = book.name
        holder.itemView.bar_score.rating = book.score
        holder.itemView.tv_score.text = "${book.score}"
        holder.itemView.tvChapterCount.text = "${book.chapterSize}章"
        holder.itemView.tv_word_size.text = BookUtils.formatWordSize(book.wordSize)
        holder.itemView.tv_click_amount.text = "点击量：${book.clickAmount}"
        holder.itemView.tvStatus.text = BookUtils.formatStatus(book.status)
        holder.itemView.tvDesc.text = book.desc
        holder.book = book
        holder.update()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var book: Book
        private val kws: ArrayList<String> = ArrayList()

        init {
            itemView.tfl_kw.adapter = object : TagAdapter<String>(kws) {
                override fun getView(parent: FlowLayout, position: Int, s: String): View {
                    val tv = LayoutInflater.from(parent.context).inflate(R.layout.item_search_word, parent, false) as TextView
                    tv.text = s
                    return tv
                }
            }

            itemView.tfl_kw.setOnTagClickListener { v, position, _ ->
                val intent = Intent(v.context, SearchActivity::class.java)
                intent.putExtra("kw", kws[position])
                v.context.startActivity(intent)
                true
            }

            itemView.tvAuthor.setOnClickListener { v ->
                val intent = Intent(v.context, SearchActivity::class.java)
//                intent.putExtra("kw", book.author)
                v.context.startActivity(intent)
            }

            itemView.tv_catalog.setOnClickListener { v ->
                val intent = Intent(v.context, CatalogActivity::class.java)
                intent.putExtra("book", book)
                v.context.startActivity(intent)
            }
        }

        fun update(): Unit {
            kws.clear()
            if (book.kw.isNotEmpty()) {
                val kw = book.kw.replace("；", ";")
                kws.addAll(kw.split(";"))
            }

            if (kws.isNotEmpty()) {
                itemView.tfl_kw.visibility = View.VISIBLE
                itemView.tfl_kw.adapter.notifyDataChanged()
            } else {
                itemView.tfl_kw.visibility = View.GONE
            }
        }
    }
}