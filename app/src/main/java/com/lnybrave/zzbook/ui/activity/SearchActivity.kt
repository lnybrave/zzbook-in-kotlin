package com.lnybrave.zzbook.ui.activity

import android.content.Context
import android.database.AbstractCursor
import android.os.Build
import android.os.Bundle
import android.support.v4.widget.SimpleCursorAdapter
import android.support.v7.widget.SearchView
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.lnybrave.zzbook.R
import com.lnybrave.zzbook.bean.SearchWord
import com.lnybrave.zzbook.di.component.DaggerSearchActivityComponent
import com.lnybrave.zzbook.di.component.SearchActivityComponent
import com.lnybrave.zzbook.di.module.ActivityModule
import com.lnybrave.zzbook.di.module.SearchSuggestModule
import com.lnybrave.zzbook.di.module.SearchWordModule
import com.lnybrave.zzbook.getAppComponent
import com.lnybrave.zzbook.mvp.contract.SearchSuggestContract
import com.lnybrave.zzbook.mvp.contract.SearchWordContract
import com.lnybrave.zzbook.mvp.presenter.SearchSuggestPresenter
import com.lnybrave.zzbook.mvp.presenter.SearchWordPresenter
import com.lnybrave.zzbook.ui.BaseActivity
import com.lnybrave.zzbook.ui.fragment.SearchFragment
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import io.reactivex.FlowableEmitter
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject


class SearchActivity : BaseActivity(), SearchWordContract.View {

    lateinit var emitter: FlowableEmitter<String>
    lateinit var mainComponent: SearchActivityComponent
    lateinit var suggestAdapter: SearchSuggestAdapter
    @Inject lateinit var wordPresenter: SearchWordPresenter
    @Inject lateinit var suggestPresenter: SearchSuggestPresenter

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

        suggestAdapter = SearchSuggestAdapter(this)
        mainComponent = DaggerSearchActivityComponent.builder()
                .appComponent(getAppComponent())
                .activityModule(ActivityModule(this))
                .searchSuggestModule(SearchSuggestModule(suggestAdapter))
                .searchWordModule(SearchWordModule(this))
                .build()
        mainComponent.inject(this)
        wordPresenter.getData()
    }

    override fun setData(results: List<SearchWord>) {
        if (results.isNotEmpty()) {
            hotWordsLayout.adapter = object : TagAdapter<SearchWord>(results) {
                override fun getView(parent: FlowLayout, position: Int, s: SearchWord): View {
                    val tv = LayoutInflater.from(applicationContext).inflate(R.layout.item_search_word, historyLayout, false) as TextView
                    tv.text = s.word
                    return tv
                }
            }
            ll_hot_words.visibility = View.VISIBLE
        } else {
            ll_hot_words.visibility = View.GONE
        }
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
        // 设置搜索图标是否显示在搜索框内
        searchView.setIconifiedByDefault(true)
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
                if (!newText.isBlank()) {
                    Log.d("lny", newText)
                    suggestPresenter.getData(newText)
                }
                return true
            }
        })
        searchView.setOnCloseListener { true }

        val editText = searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text) as EditText
        editText.textSize = 14f
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            editText.setTextColor(resources.getColor(R.color.text_color_2, null))
            editText.setHintTextColor(resources.getColor(R.color.text_hint, null))
        } else {
            editText.setTextColor(resources.getColor(R.color.text_color_2))
            editText.setHintTextColor(resources.getColor(R.color.text_hint))
        }

        searchView.suggestionsAdapter = suggestAdapter
        searchView.setOnSuggestionListener(object : SearchView.OnSuggestionListener {
            override fun onSuggestionSelect(position: Int): Boolean {
                return false
            }

            override fun onSuggestionClick(position: Int): Boolean {
                val word = suggestAdapter.results.get(position).word
                searchBook(word)
                editText.setText(word)
                editText.setSelection(word.length)
                return false
            }
        })
    }

    class SearchSuggestAdapter(context: Context)
        : SimpleCursorAdapter(context, android.R.layout.simple_list_item_1, null,
            SearchActivity.SuggestionsCursor.mVisible,
            SearchActivity.SuggestionsCursor.mViewIds, 0), SearchSuggestContract.View {

        var results: ArrayList<SearchWord> = ArrayList()

        override fun setData(results: List<SearchWord>) {
            this.results.clear()
            this.results.addAll(results)
            this.changeCursor(SuggestionsCursor(this.results))
            notifyDataSetChanged()
        }
    }

    private class SuggestionsCursor(results: ArrayList<SearchWord>) : AbstractCursor() {

        private val mResults: ArrayList<String> = ArrayList()

        init {
            val it = results.iterator()
            while (it.hasNext()) {
                mResults.add(it.next().word)
            }
        }

        override fun getCount(): Int {
            return mResults.size
        }

        override fun getColumnNames(): Array<String> {
            return SuggestionsCursor.mFields
        }

        override fun getLong(column: Int): Long {
            if (column == 0) {
                return position.toLong()
            }
            throw UnsupportedOperationException("unimplemented")
        }

        override fun getString(column: Int): String {
            if (column == 1) {
                return mResults[position]
            }
            throw UnsupportedOperationException("unimplemented")
        }

        override fun getShort(column: Int): Short {
            throw UnsupportedOperationException("unimplemented")
        }

        override fun getInt(column: Int): Int {
            throw UnsupportedOperationException("unimplemented")
        }

        override fun getFloat(column: Int): Float {
            throw UnsupportedOperationException("unimplemented")
        }

        override fun getDouble(column: Int): Double {
            throw UnsupportedOperationException("unimplemented")
        }

        override fun isNull(column: Int): Boolean {
            return false
        }

        companion object {

            val mFields = arrayOf("_id", "result")
            val mVisible = arrayOf("result")
            val mViewIds = intArrayOf(android.R.id.text1)
        }
    }

}
