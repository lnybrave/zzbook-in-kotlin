package com.lnybrave.zzbook.ui.activity

import android.os.Bundle
import android.support.v7.widget.SearchView
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import com.lnybrave.zzbook.R
import com.lnybrave.zzbook.di.component.DaggerMainComponent
import com.lnybrave.zzbook.di.component.MainComponent
import com.lnybrave.zzbook.di.module.ActivityModule
import com.lnybrave.zzbook.getAppComponent
import com.lnybrave.zzbook.ui.BaseActivity
import com.lnybrave.zzbook.ui.fragment.SearchFragment
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.FlowableEmitter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.toolbar.*
import java.util.concurrent.TimeUnit


class SearchActivity : BaseActivity() {

    lateinit var emitter: FlowableEmitter<String>
    lateinit var mainComponent: MainComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        initView()
    }

    override fun initView() {
        setupToolbar(toolbar)

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

        Flowable.create<String>({ e -> emitter = e }, BackpressureStrategy.LATEST)
                .flatMap { s -> Flowable.just(s).delay(1, TimeUnit.SECONDS) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ s -> autoSearch(s) })

        mainComponent = DaggerMainComponent.builder()
                .appComponent(getAppComponent())
                .activityModule(ActivityModule(this))
                .build()
    }

    fun autoSearch(s: String) {
        Log.d("lny", s)
    }

    fun searchBook(s: String) {
        if (!isFinishing) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment, SearchFragment.newInstance(s))
                    .commitAllowingStateLoss()
            llSearchWord.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search2, menu)
        val search = menu?.findItem(R.id.item_search)
        if (search != null) {
            initSearchView(search)
        }
        return super.onCreateOptionsMenu(menu)
    }

    private fun initSearchView(menuItem: MenuItem) {
        // 展开搜索框
        menuItem.collapseActionView()
        menuItem.expandActionView()

        val searchView = menuItem.actionView as SearchView
        searchView.isIconified = false
        searchView.clearFocus()
        searchView.queryHint = Html.fromHtml("<font color = #999999>" + resources.getString(R.string.hint_search) + "</font>")
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (!query.isBlank()) {
                    searchBook(query.trim())
                }
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                emitter.onNext(newText)
                return true
            }
        })
        searchView.setOnCloseListener { true }

//        val id = searchView.context.resources.getIdentifier("android:id/search_src_text", null, null)
//        val editText = searchView.findViewById(id) as EditText
//        editText.setTextColor(resources.getColor(R.color.text_color_2))
//        editText.textSize = resources.getDimension(R.dimen.text_size)
//        editText.setHintTextColor(resources.getColor(R.color.text_hint))
    }

}
