package com.lnybrave.zzbook.ui

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.lnybrave.zzbook.R

abstract class BaseActivity : AppCompatActivity() {

    abstract fun initView()

    fun setupToolbar(toolbar: Toolbar) {
        toolbar.title = ""
        toolbar.setNavigationIcon(R.drawable.ic_back)
        setSupportActionBar(toolbar)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

}