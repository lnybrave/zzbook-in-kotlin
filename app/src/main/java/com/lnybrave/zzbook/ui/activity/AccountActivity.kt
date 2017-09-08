package com.lnybrave.zzbook.ui.activity

import android.content.Intent
import android.os.Bundle
import com.lnybrave.zzbook.R
import com.lnybrave.zzbook.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_account.*
import kotlinx.android.synthetic.main.toolbar.*

class AccountActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)
    }

    override fun initView() {
        setupToolbar(toolbar)
        tvTitle.text = resources.getString(R.string.title_account)

        btn_logout.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}
