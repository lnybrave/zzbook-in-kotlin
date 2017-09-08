package com.lnybrave.zzbook.ui.activity

import android.os.Bundle
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

    private val mHot: ArrayList<Any> = ArrayList()
    private lateinit var mHotAdapter: MultiTypeAdapter
    @Inject lateinit var mHotPresenter: SearchHotPresenter

    private val mSuggest: ArrayList<SearchWord> = ArrayList()
    private lateinit var mSuggestAdapter: MultiTypeAdapter
    @Inject lateinit var mSuggestPresenter: SearchSuggestPresenter

    private val mSearch: ArrayList<Any> = ArrayList()
    private lateinit var mSearchAdapter: MultiTypeAdapter
    @Inject lateinit var mSearchPresenter: SearchPresenter
    private var offset: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
    }

    override fun initView() {
        setupToolbar(toolbar)

        setupSearchView(search_view)


        skipIds = ArrayList()
        skipIds.add(R.id.rl_toolbar)
        setupProgressWidget(progress)

        setupHotViews()

        setupSuggestViews()

        setupSearchViews()

        mainComponent = DaggerSearchActivityComponent.builder()
                .appComponent(getAppComponent())
                .activityModule(ActivityModule(this))
                .searchSuggestModule(SearchSuggestModule(this))
                .searchHotModule(SearchHotModule(this))
                .searchActivityModule(SearchActivityModule(this))
                .build()
        mainComponent.inject(this)

        initData()

        val kw = intent.getStringExtra("kw")
        if (kw != null && kw.isNotEmpty()) {
            search_view.setQuery(kw, true)
        }
    }

    private fun setupHotViews() {
        mHotAdapter = MultiTypeAdapter(mHot)
        rv_hot.layoutManager = LinearLayoutManager(this)
        rv_hot.adapter = mHotAdapter
        mHotAdapter.register(Book::class.java, BookComplexViewBinder())
        mHotAdapter.register(SearchTitle::class.java, SearchTitleViewBinder())
        val searchTagViewBinder = SearchTagViewBinder()
        searchTagViewBinder.onTagClickListener = object : SearchTagViewBinder.OnTagClickListener {
            override fun onTagClick(word: SearchWord) {
                search_view.setQuery(word.word, true)
            }
        }
        mHotAdapter.register(SearchTag::class.java, searchTagViewBinder)
    }

    private fun setupSuggestViews() {
        mSuggestAdapter = MultiTypeAdapter(mSuggest)
        rv_suggest.layoutManager = LinearLayoutManager(this)
        rv_suggest.adapter = mSuggestAdapter
        mSuggestAdapter.register(Book::class.java, BookComplexViewBinder())
        mSuggestAdapter.register(SearchTitle::class.java, SearchTitleViewBinder())
        val searchSuggestViewBinder = SearchSuggestViewBinder()
        searchSuggestViewBinder.onItemClickListener = object : SearchSuggestViewBinder.OnItemClickListener {
            override fun onItemClick(word: SearchWord) {
                search_view.setQuery(word.word, false)
                search(word.word)
            }
        }
        mSuggestAdapter.register(SearchWord::class.java, searchSuggestViewBinder)
    }

    private fun setupSearchViews() {
        refreshLayout.isEnableRefresh = false
        refreshLayout.setOnLoadmoreListener { _ -> searchMore() }

        mSearchAdapter = MultiTypeAdapter(mSearch)
        rv_search.layoutManager = LinearLayoutManager(this)
        rv_search.adapter = mSearchAdapter
        mSearchAdapter.register(Book::class.java, BookComplexViewBinder())
        mSearchAdapter.register(Integer::class.java, SearchCountViewBinder())
    }

    override fun initData() {
        mHotPresenter.getData()
    }

    override fun setData(data: Search) {
        mHot.clear()

        if (data.words.isNotEmpty()) {
            mHot.add(SearchTitle("大家都在搜", data.words))
            mHot.add(SearchTag(data.words))
        }

        if (data.books.isNotEmpty()) {
            mHot.add(SearchTitle("热搜top榜", data.words))
            mHot.addAll(data.books)
        }

        mHotAdapter.notifyDataSetChanged()
    }

    override fun setData(data: List<SearchWord>) {
        mSuggest.clear()
        if (data.isNotEmpty()) {
            mSuggest.addAll(data)
        }
        mSuggestAdapter.notifyDataSetChanged()
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
            mSearch.clear()
            offset = 0
            mSearch.add(data.count)
        }

        refreshLayout.isEnableLoadmore = data.hasNext()

        if (data.results.isNotEmpty()) {
            mSearch.addAll(data.results)
        }

        offset += data.results.size
        mSearchAdapter.notifyDataSetChanged()
        search_view.clearFocus()
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
        mHotPresenter.unSubscribe()
        mSuggestPresenter.unSubscribe()
        mSearchPresenter.unSubscribe()
        super.onDestroy()
    }

    private fun setupSearchView(searchView: SearchView) {
        searchView.setIconifiedByDefault(false)
        searchView.queryHint = resources.getString(R.string.hint_search)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (!query.isBlank()) {
                    searchView.clearFocus()
                    search(query.trim())
                    return false
                }
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (!newText.isBlank()) {
                    suggest(newText)
                } else {
                    // 没有焦点时，直接清空内容不会调此回调
                    showHotLayout()
                }
                return true
            }
        })

        searchView.setOnQueryTextFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                if (searchView.query.isEmpty()) {
                    showHotLayout()
                }
            } else {
                showSearchLayout()
            }
        }

        //没有执行
        searchView.setOnCloseListener {
            showHotLayout()
            true
        }
    }

    fun showHotLayout() {
        rv_hot.visibility = View.VISIBLE
        rv_suggest.visibility = View.GONE
        refreshLayout.visibility = View.GONE
        showContent()
    }

    fun showSuggestLayout() {
        rv_hot.visibility = View.GONE
        rv_suggest.visibility = View.VISIBLE
        refreshLayout.visibility = View.GONE
        showContent()
    }

    fun showSearchLayout() {
        rv_hot.visibility = View.GONE
        rv_suggest.visibility = View.GONE
        refreshLayout.visibility = View.VISIBLE
        showContent()
    }

    var suggestWrapper: SuggestWrapper = SuggestWrapper()

    fun suggest(word: String) {
        showSuggestLayout()
        if (suggestWrapper.cache != word) {
            if (!word.startsWith(suggestWrapper.cache)) {
                mSuggest.clear()
                mSuggestAdapter.notifyDataSetChanged()
            }
            suggestWrapper.suggest(word)
        }
    }

    inner class SuggestWrapper {

        var cache: String = ""

        fun suggest(s: String) {
            cache = s
            mSuggestPresenter.getData(s)
        }
    }

    var searchWrapper: SearchWrapper = SearchWrapper()

    fun search(s: String) {
        offset = 0
        showSearchLayout()
        searchWrapper.search(s)
    }

    fun searchMore() {
        searchWrapper.search()
    }

    inner class SearchWrapper {

        var cache: String = ""

        fun search(s: String) {
            cache = s
            mSearchPresenter.getData(cache, offset)
        }

        fun search() {
            mSearchPresenter.getData(cache, offset)
        }
    }
}
