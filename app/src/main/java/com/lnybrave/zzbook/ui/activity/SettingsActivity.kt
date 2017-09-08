package com.lnybrave.zzbook.ui.activity

import android.os.Bundle
import com.lnybrave.zzbook.R
import com.lnybrave.zzbook.ui.BaseActivity
import kotlinx.android.synthetic.main.toolbar.*

class SettingsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
    }

    override fun initView() {
        setupToolbar(toolbar)
        tvTitle.text = resources.getString(R.string.title_settings)
    }

}
