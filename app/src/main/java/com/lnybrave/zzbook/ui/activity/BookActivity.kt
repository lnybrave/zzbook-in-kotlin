package com.lnybrave.zzbook.ui.activity

import android.os.Bundle
import com.lnybrave.zzbook.R
import com.lnybrave.zzbook.bean.Book
import com.lnybrave.zzbook.ui.BaseActivity
import kotlinx.android.synthetic.main.toolbar.*

class BookActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)
        initView()
    }

    override fun initView() {
        setupToolbar(toolbar)
        val book = intent.getSerializableExtra("book") as Book
        tvTitle.text = book.name
    }
}
