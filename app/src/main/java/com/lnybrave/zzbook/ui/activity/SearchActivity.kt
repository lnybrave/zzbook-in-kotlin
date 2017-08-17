package com.lnybrave.zzbook.ui.activity

import android.content.Context
import android.database.AbstractCursor
import android.os.Build
import android.os.Bundle
import android.support.v4.widget.SimpleCursorAdapter
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.text.Html
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.lnybrave.zzbook.R
import com.lnybrave.zzbook.bean.Book
import com.lnybrave.zzbook.bean.Search
import com.lnybrave.zzbook.bean.SearchWord
import com.lnybrave.zzbook.di.component.DaggerSearchActivityComponent
import com.lnybrave.zzbook.di.component.SearchActivityComponent
import com.lnybrave.zzbook.di.module.ActivityModule
import com.lnybrave.zzbook.di.module.SearchHotModule
import com.lnybrave.zzbook.di.module.SearchSuggestModule
import com.lnybrave.zzbook.getAppComponent
import com.lnybrave.zzbook.mvp.contract.SearchHotContract
import com.lnybrave.zzbook.mvp.contract.SearchSuggestContract
import com.lnybrave.zzbook.mvp.presenter.SearchHotPresenter
import com.lnybrave.zzbook.mvp.presenter.SearchSuggestPresenter
import com.lnybrave.zzbook.ui.BaseActivity
import com.lnybrave.zzbook.ui.fragment.SearchFragment
import com.lnybrave.zzbook.ui.multitype.BookComplexViewBinder
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.toolbar.*
import me.drakeet.multitype.MultiTypeAdapter
import javax.inject.Inject


class SearchActivity : BaseActivity(), SearchHotContract.View {

    lateinit var mainComponent: SearchActivityComponent
    lateinit var suggestAdapter: SearchSuggestAdapter
    @Inject lateinit var hotPresenter: SearchHotPresenter
    @Inject lateinit var suggestPresenter: SearchSuggestPresenter
    private lateinit var adapter: MultiTypeAdapter
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        initView()
    }

    override fun initView() {
        setupToolbar(toolbar)

        ll_history_words.visibility = View.GONE
        ll_hot_words.visibility = View.GONE

        rv_hot_books.layoutManager = LinearLayoutManager(this)
        ll_hot_books.visibility = View.GONE


        adapter = MultiTypeAdapter()
        rv_hot_books.adapter = adapter
        adapter.register(Book::class.java, BookComplexViewBinder())

        suggestAdapter = SearchSuggestAdapter(this)
        mainComponent = DaggerSearchActivityComponent.builder()
                .appComponent(getAppComponent())
                .activityModule(ActivityModule(this))
                .searchSuggestModule(SearchSuggestModule(suggestAdapter))
                .searchHotModule(SearchHotModule(this))
                .build()
        mainComponent.inject(this)
        hotPresenter.getData()
    }

    override fun setData(results: Search) {
        if (results.words.isNotEmpty()) {
            hotWordsLayout.adapter = object : TagAdapter<SearchWord>(results.words) {
                override fun getView(parent: FlowLayout, position: Int, s: SearchWord): View {
                    val tv = LayoutInflater.from(applicationContext).inflate(R.layout.item_search_word, historyLayout, false) as TextView
                    tv.text = s.word
                    return tv
                }
            }
            hotWordsLayout.setOnTagClickListener { view, position, parent ->
                searchView.setQuery(results.words[position].word, true)
                true
            }
            ll_hot_words.visibility = View.VISIBLE
        } else {
            ll_hot_words.visibility = View.GONE
        }

        if (results.books.isNotEmpty()) {
            adapter.items = results.books
            adapter.notifyDataSetChanged()
            ll_hot_books.visibility = View.VISIBLE
        } else {
            ll_hot_books.visibility = View.GONE
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

        searchView = menuItem.actionView as SearchView
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
                    suggestPresenter.getData(newText)
                } else {
                    llSearchWord.visibility = View.VISIBLE
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
