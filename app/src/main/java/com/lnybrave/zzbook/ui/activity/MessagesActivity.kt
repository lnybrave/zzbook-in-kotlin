package com.lnybrave.zzbook.ui.activity

import android.os.Bundle
import com.lnybrave.zzbook.R
import com.lnybrave.zzbook.ui.ProgressActivity
import kotlinx.android.synthetic.main.toolbar.*

class MessagesActivity : ProgressActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_messages)
    }

    override fun initView() {
        setupToolbar(toolbar)
        tvTitle.text = resources.getString(R.string.title_my_messages)
    }
}
