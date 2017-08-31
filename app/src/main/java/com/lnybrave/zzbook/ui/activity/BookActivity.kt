package com.lnybrave.zzbook.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.lnybrave.zzbook.R
import com.lnybrave.zzbook.bean.Book
import com.lnybrave.zzbook.di.component.DaggerMainComponent
import com.lnybrave.zzbook.di.component.MainComponent
import com.lnybrave.zzbook.di.module.ActivityModule
import com.lnybrave.zzbook.di.module.BookDetailModule
import com.lnybrave.zzbook.getAppComponent
import com.lnybrave.zzbook.mvp.IPresenter
import com.lnybrave.zzbook.mvp.contract.BookDetailContract
import com.lnybrave.zzbook.mvp.presenter.BookDetailPresenter
import com.lnybrave.zzbook.ui.ProgressActivity
import com.lnybrave.zzbook.ui.multitype.BookDetailViewBinder
import com.malinskiy.materialicons.IconDrawable
import com.malinskiy.materialicons.Iconify
import kotlinx.android.synthetic.main.activity_book.*
import kotlinx.android.synthetic.main.toolbar.*
import me.drakeet.multitype.MultiTypeAdapter
import java.util.*
import javax.inject.Inject

class BookActivity : ProgressActivity(), BookDetailContract.View {

    private lateinit var book: Book
    private lateinit var skipIds: ArrayList<Int>
    private var mList = ArrayList<Any>()
    private lateinit var mAdapter: MultiTypeAdapter
    lateinit var mainComponent: MainComponent
    @Inject lateinit var mPresenter: BookDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)
        book = intent.getSerializableExtra("book") as Book
        initView()
        initData()
    }

    override fun initView() {
        setupToolbar(toolbar)
        tvTitle.text = book.name

        setupProgressWidget(progress)
        skipIds = ArrayList()
        skipIds.add(R.id.rl_toolbar)

        mAdapter = MultiTypeAdapter(mList)
        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        mAdapter.register(Book::class.java, BookDetailViewBinder())

        mainComponent = DaggerMainComponent.builder()
                .appComponent(getAppComponent())
                .activityModule(ActivityModule(this))
                .build()
        mainComponent.plus(BookDetailModule(this)).inject(this)
    }

    fun initData() {
        mPresenter.getData(book.id)
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

    override fun setData(data: Book) {
        mList.add(data)
        mAdapter.notifyDataSetChanged()
    }

    override fun onLoadStart(presenter: IPresenter) {
        showLoading(skipIds)
    }

    override fun onLoadStop(presenter: IPresenter) {
        showContent()
    }
}
