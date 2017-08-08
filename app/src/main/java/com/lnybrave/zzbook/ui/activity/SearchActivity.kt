package com.lnybrave.zzbook.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import com.lnybrave.zzbook.R
import com.lnybrave.zzbook.toast
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import kotlinx.android.synthetic.main.activity_search.*


class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        initTitle()

        initViews()
    }

    private fun initTitle() {
        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_nav_back)
        toolbar.setNavigationOnClickListener { finish() }
    }

    private fun initViews() {
        val mVals = ArrayList<String>()
        mVals.add("大秦帝国")
        mVals.add("我的极品女老师")

        historyLayout.adapter = object : TagAdapter<String>(mVals) {
            override fun getView(parent: FlowLayout, position: Int, s: String): View {
                val tv = LayoutInflater.from(applicationContext).inflate(R.layout.item_search_word, historyLayout, false) as TextView
                tv.text = s
                return tv
            }
        }

        hotWordsLayout.adapter = object : TagAdapter<String>(mVals) {
            override fun getView(parent: FlowLayout, position: Int, s: String): View {
                val tv = LayoutInflater.from(applicationContext).inflate(R.layout.item_search_word, historyLayout, false) as TextView
                tv.text = s
                return tv
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search2, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_search -> {
                toast("search")
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
