package com.lnybrave.zzbook.ui.activity

import android.os.Bundle
import com.lnybrave.zzbook.R
import com.lnybrave.zzbook.bean.Book
import com.lnybrave.zzbook.ui.ProgressActivity
import kotlinx.android.synthetic.main.toolbar.*

class CatalogActivity : ProgressActivity() {

    private lateinit var book: Book

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        book = intent.getSerializableExtra("book") as Book
        setContentView(R.layout.activity_catalog)
    }

    override fun initView() {
        setupToolbar(toolbar)
        tvTitle.text = book.name
    }
}
