package com.lnybrave.zzbook.ui.activity

import android.database.AbstractCursor
import android.os.Bundle
import android.support.v4.widget.SimpleCursorAdapter
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.View
import com.lnybrave.zzbook.R
import com.lnybrave.zzbook.bean.APIPage
import com.lnybrave.zzbook.bean.Book
import com.lnybrave.zzbook.bean.Search
import com.lnybrave.zzbook.bean.SearchWord
import com.lnybrave.zzbook.di.component.DaggerSearchActivityComponent
import com.lnybrave.zzbook.di.component.SearchActivityComponent
import com.lnybrave.zzbook.di.module.ActivityModule
import com.lnybrave.zzbook.di.module.SearchActivityModule
import com.lnybrave.zzbook.di.module.SearchHotModule
import com.lnybrave.zzbook.di.module.SearchSuggestModule
import com.lnybrave.zzbook.getAppComponent
import com.lnybrave.zzbook.mvp.IPresenter
import com.lnybrave.zzbook.mvp.contract.SearchContract
import com.lnybrave.zzbook.mvp.contract.SearchHotContract
import com.lnybrave.zzbook.mvp.contract.SearchSuggestContract
import com.lnybrave.zzbook.mvp.presenter.SearchHotPresenter
import com.lnybrave.zzbook.mvp.presenter.SearchPresenter
import com.lnybrave.zzbook.mvp.presenter.SearchSuggestPresenter
import com.lnybrave.zzbook.ui.ProgressActivity
import com.lnybrave.zzbook.ui.multitype.*
import com.malinskiy.materialicons.IconDrawable
import com.malinskiy.materialicons.Iconify
import kotlinx.android.synthetic.main.activity_search.*
import me.drakeet.multitype.MultiTypeAdapter
import javax.inject.Inject


class SearchActivity : ProgressActivity(), SearchHotContract.View, SearchSuggestContract.View, SearchContract.View {

    lateinit var skipIds: ArrayList<Int>
    lateinit var mainComponent: SearchActivityComponent
    lateinit var suggestAdapter: SimpleCursorAdapter
    @Inject lateinit var suggestPresenter: SearchSuggestPresenter
    @Inject lateinit var hotPresenter: SearchHotPresenter
    @Inject lateinit var mPresenter: SearchPresenter

    private var mList: ArrayList<Any> = ArrayList()
    private lateinit var mAdapter: MultiTypeAdapter

    private val mHotCache: ArrayList<Any> = ArrayList()
    private val mSuggestCache: ArrayList<SearchWord> = ArrayList()
    private val mSearchCache: ArrayList<Book> = ArrayList()
    private var offset: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        initView()
    }

    override fun initView() {
        setupToolbar(toolbar)

        setupSearchView(search_view)

        skipIds = ArrayList()
        skipIds.add(R.id.rl_toolbar)
        setupProgressWidget(progress)

        refreshLayout.isEnableRefresh = false
        refreshLayout.setOnLoadmoreListener { _ -> search(suggestWord) }

        mAdapter = MultiTypeAdapter(mList)
        rv_hot_books.layoutManager = LinearLayoutManager(this)
        rv_hot_books.adapter = mAdapter
        mAdapter.register(Book::class.java, BookComplexViewBinder())
        mAdapter.register(SearchWordsTitle::class.java, SearchWordsTitleViewBinder())
        val searchTagViewBinder = SearchTagViewBinder()
        searchTagViewBinder.onTagClickListener = object : SearchTagViewBinder.OnTagClickListener {
            override fun onTagClick(word: SearchWord) {
                search_view.setQuery(word.word, true)
            }
        }
        mAdapter.register(SearchTag::class.java, searchTagViewBinder)

        mainComponent = DaggerSearchActivityComponent.builder()
                .appComponent(getAppComponent())
                .activityModule(ActivityModule(this))
                .searchSuggestModule(SearchSuggestModule(this))
                .searchHotModule(SearchHotModule(this))
                .searchActivityModule(SearchActivityModule(this))
                .build()
        mainComponent.inject(this)
        initData()
    }

    private fun initData() {
        hotPresenter.getData()
    }

    override fun setData(data: Search) {
        mHotCache.clear()

        if (data.words.isNotEmpty()) {
            mHotCache.add(SearchWordsTitle("大家都在搜", data.words))
            mHotCache.add(SearchTag(data.words))
        }

        if (data.books.isNotEmpty()) {
            mHotCache.add(SearchWordsTitle("热搜top榜", data.words))
            mHotCache.addAll(data.books)
        }

        mList.addAll(mHotCache)
        mAdapter.notifyDataSetChanged()
    }

    fun search(s: String) {
        mPresenter.getData(s, offset)
    }

    override fun onEmpty(presenter: IPresenter) {
        val emptyDrawable = IconDrawable(this, Iconify.IconValue.zmdi_shopping_basket)
                .colorRes(android.R.color.white)

        showEmpty(emptyDrawable,
                "Empty Shopping Cart",
                "Please add things in the cart to continue.",
                skipIds)
    }

    override fun onError(presenter: IPresenter, message: String?) {
        val errorDrawable = IconDrawable(this, Iconify.IconValue.zmdi_wifi_off)
                .colorRes(android.R.color.white)

        showError(errorDrawable,
                "No Connection",
                "We could not establish a connection with our servers. Please try again when you are connected to the internet.",
                "重试",
                View.OnClickListener {
                    initData()
                },
                skipIds)
    }

    override fun setData(data: APIPage<Book>) {
        if (!data.hasPrev()) {
            mSearchCache.clear()
            mList.clear()
            offset = 0
        }

        refreshLayout.isEnableLoadmore = data.hasNext()

        if (data.results.isNotEmpty()) {
            mSearchCache.addAll(data.results)
            mList.addAll(data.results)
        }

        offset += data.results.size
        mAdapter.notifyDataSetChanged()
    }

    override fun onLoadStart(presenter: IPresenter) {
        if (!refreshLayout.isRefreshing && !refreshLayout.isLoading) {
            showLoading(skipIds)
        }
    }

    override fun onLoadStop(presenter: IPresenter) {
        refreshLayout.finishRefresh()
        refreshLayout.finishLoadmore()
        showContent()
    }

    override fun onDestroy() {
        mPresenter.unSubscribe()
        suggestPresenter.unSubscribe()
        hotPresenter.unSubscribe()
        super.onDestroy()
    }

    private fun setupSearchView(searchView: SearchView) {
        searchView.setIconifiedByDefault(false)
        searchView.queryHint = resources.getString(R.string.hint_search)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (!query.isBlank()) {
                    searchView.clearFocus()
                    offset = 0
                    search(query.trim())
                    return false
                }
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (!newText.isBlank()) {
                    if (mList.isNotEmpty()) {
                        mList.clear()
                        mAdapter.notifyDataSetChanged()
                    }
                    getSuggestList(newText)
                } else {
                    // 没有焦点时，直接清空内容不会调此回调
                    showHot()
                }
                return true
            }
        })

        searchView.setOnQueryTextFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                if (searchView.query.isEmpty()) {
                    showHot()
                } else {
                    showSuggest()
                }
            } else {
                showSearch()
            }
        }

        //没有执行
        searchView.setOnCloseListener {
            showHot()
            true
        }

        suggestAdapter = SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, null,
                SearchActivity.SuggestionsCursor.mVisible,
                SearchActivity.SuggestionsCursor.mViewIds, 0)
        searchView.suggestionsAdapter = suggestAdapter

        searchView.setOnSuggestionListener(object : SearchView.OnSuggestionListener {
            override fun onSuggestionSelect(position: Int): Boolean {
                return false
            }

            override fun onSuggestionClick(position: Int): Boolean {
                val item = suggestResults[position]
                val word = item.word
                searchView.setQuery(word, true)
                return false
            }
        })
    }

    fun showHot() {
        mList.clear()
        mList.addAll(mHotCache)
        mAdapter.notifyDataSetChanged()
        showContent()
    }

    fun showSuggest() {
        mList.clear()
        mList.addAll(mSuggestCache)
        mAdapter.notifyDataSetChanged()
        showContent()
    }

    fun showSearch() {
        mList.clear()
        mList.addAll(mSearchCache)
        mAdapter.notifyDataSetChanged()
        showContent()
    }

    fun getSuggestList(word: String) {
        if (suggestWord != word) {
            suggestWord = word
            suggestPresenter.getData(word)
        }
    }

    var suggestWord: String = ""
    var suggestResults: ArrayList<SearchWord> = ArrayList()

    override fun setData(data: List<SearchWord>) {
        suggestResults.clear()
        suggestResults.addAll(data)
        suggestAdapter.changeCursor(SuggestionsCursor(suggestResults))
        suggestAdapter.notifyDataSetChanged()
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
