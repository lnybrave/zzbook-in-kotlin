package com.lnybrave.zzbook.ui.fragment

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lnybrave.zzbook.Constants
import com.lnybrave.zzbook.bean.APIPage
import com.lnybrave.zzbook.bean.Book
import com.lnybrave.zzbook.bean.MixedBean
import com.lnybrave.zzbook.bean.Topic
import com.lnybrave.zzbook.databinding.ViewRecyclerBinding
import com.lnybrave.zzbook.di.module.ColumnDetailModule
import com.lnybrave.zzbook.mvp.IPresenter
import com.lnybrave.zzbook.mvp.contract.ColumnDetailContract
import com.lnybrave.zzbook.mvp.presenter.ColumnDetailPresenter
import com.lnybrave.zzbook.ui.BaseBindingFragment
import com.lnybrave.zzbook.ui.activity.ColumnActivity
import com.lnybrave.zzbook.ui.multitype.BookComplexViewBinder
import com.lnybrave.zzbook.ui.multitype.BookSimpleViewBinder
import com.lnybrave.zzbook.ui.multitype.TopicTitleViewBinder
import com.malinskiy.materialicons.IconDrawable
import com.malinskiy.materialicons.Iconify
import kotlinx.android.synthetic.main.view_recycler.*
import me.drakeet.multitype.MultiTypeAdapter
import java.util.*
import javax.inject.Inject


class ColumnDetailFragment : BaseBindingFragment<ViewRecyclerBinding>(), ColumnDetailContract.View {

    private var columnId: Int? = null
    private var mList = ArrayList<Any>()
    private lateinit var mAdapter: MultiTypeAdapter
    @Inject lateinit var mPresenter: ColumnDetailPresenter
    private var offset: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            columnId = arguments.getInt(ColumnDetailFragment.COLUMN_ID)
        }
    }

    override fun createDataBinding(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): ViewRecyclerBinding {
        return ViewRecyclerBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        mAdapter = MultiTypeAdapter(mList)

        with(mBinding) {
            setupProgressWidget(progress)

            refreshLayout.setOnRefreshListener({ mPresenter.getData(columnId!!) })
            refreshLayout.setOnLoadmoreListener({ initData() })

            recyclerView.adapter = mAdapter
            val layoutManager = GridLayoutManager(context, 3)
            layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    val obj = mList[position]
                    if (obj is Book) {
                        return if (obj.showType == 0) 1 else 3
                    }
                    return 3
                }
            }
            recyclerView.layoutManager = layoutManager

            mAdapter.register(Topic::class.java, TopicTitleViewBinder())
            mAdapter.register(Book::class.java)
                    .to(BookSimpleViewBinder(), BookComplexViewBinder())
                    .withLinker { book -> book.showType }
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity is ColumnActivity) {
            val a: ColumnActivity = activity as ColumnActivity
            a.mainComponent.plus(ColumnDetailModule(this)).inject(this)
        } else {
            throw IllegalArgumentException("is not ColumnActivity")
        }
    }

    override fun onUserVisible(firstVisible: Boolean) {
        if (firstVisible) {
            initData()
        }
    }

    private fun initData() {
        mPresenter.getData(columnId!!, offset)
    }

    override fun onEmpty(presenter: IPresenter) {
        val emptyDrawable = IconDrawable(this.activity, Iconify.IconValue.zmdi_shopping_basket)
                .colorRes(android.R.color.white)

        showEmpty(emptyDrawable,
                "Empty Shopping Cart",
                "Please add things in the cart to continue.")
    }

    override fun onError(presenter: IPresenter, message: String?) {
        val errorDrawable = IconDrawable(this.activity, Iconify.IconValue.zmdi_wifi_off)
                .colorRes(android.R.color.white)

        showError(errorDrawable,
                "No Connection",
                "We could not establish a connection with our servers. Please try again when you are connected to the internet.",
                "重试",
                View.OnClickListener {
                    initData()
                })
    }

    override fun setData(page: APIPage<MixedBean>) {
        if (!page.hasPrev()) {
            mList.clear()
            offset = 0
        }

        refreshLayout.isEnableLoadmore = page.hasNext()

        for ((book, topic) in page.results) {
            if (book != null) {
                mList.add(book)
            }
            if (topic != null) {
                if (topic.books != null) {
                    if (topic.books.isNotEmpty()) {
                        mList.add(topic)
                        for (b in topic.books) {
                            b.showType = if (topic.type == Constants.TOPIC_SIMPLE) 0 else 1
                        }
                        mList.addAll(topic.books)
                    }
                }
            }
        }

        offset += page.results.size

        mAdapter.notifyDataSetChanged()
    }

    override fun onLoadStart(presenter: IPresenter) {
        if (!refreshLayout.isRefreshing && !refreshLayout.isLoading) {
            showLoading()
        }
    }

    override fun onLoadStop(presenter: IPresenter) {
        refreshLayout.finishRefresh()
        refreshLayout.finishLoadmore()
        showContent()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mPresenter.unSubscribe()
    }

    companion object {

        private val COLUMN_ID = "column_id"

        fun newInstance(columnId: Int): ColumnDetailFragment {
            val fragment = ColumnDetailFragment()
            val args = Bundle()
            args.putInt(COLUMN_ID, columnId)
            fragment.arguments = args
            return fragment
        }
    }
}
