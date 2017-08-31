package com.lnybrave.zzbook.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import com.lnybrave.zzbook.R
import com.lnybrave.zzbook.bean.Book
import com.lnybrave.zzbook.databinding.ViewRecyclerBinding
import com.lnybrave.zzbook.di.module.BookshelfModule
import com.lnybrave.zzbook.mvp.IPresenter
import com.lnybrave.zzbook.mvp.contract.BookshelfContract
import com.lnybrave.zzbook.mvp.presenter.BookshelfPresenter
import com.lnybrave.zzbook.toast
import com.lnybrave.zzbook.ui.BaseBindingFragment
import com.lnybrave.zzbook.ui.activity.MainActivity
import com.lnybrave.zzbook.ui.activity.SearchActivity
import com.lnybrave.zzbook.ui.multitype.BookshelfViewBinder
import kotlinx.android.synthetic.main.view_recycler.*
import me.drakeet.multitype.MultiTypeAdapter
import java.util.*
import javax.inject.Inject


class BookshelfFragment : BaseBindingFragment<ViewRecyclerBinding>(), BookshelfContract.View {

    private var mList = ArrayList<Any>()
    private lateinit var mAdapter: MultiTypeAdapter
    @Inject lateinit var mPresenter: BookshelfPresenter

    override fun createDataBinding(inflater: LayoutInflater?, container: ViewGroup?,
                                   savedInstanceState: Bundle?): ViewRecyclerBinding {
        return ViewRecyclerBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        initTitle()

        mAdapter = MultiTypeAdapter(mList)

        with(mBinding) {
            setupProgressWidget(progress)

            refreshLayout.setOnRefreshListener({ mPresenter.getData() })
            refreshLayout.setOnLoadmoreListener({ mPresenter.getData() })

            recyclerView.adapter = mAdapter
            recyclerView.layoutManager = LinearLayoutManager(context)
            mAdapter.register(Book::class.java, BookshelfViewBinder())
        }
    }

    private fun initTitle() {
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity is MainActivity) {
            val a: MainActivity = activity as MainActivity
            a.mainComponent.plus(BookshelfModule(this)).inject(this)
            mPresenter.getData()
        } else {
            throw IllegalArgumentException("is not MainActivity")
        }
    }

    override fun onError(presenter: IPresenter, message: String?) {
        refreshLayout.finishRefresh()
        showContent()
    }

    override fun onLoadStart(presenter: IPresenter) {
        if (!refreshLayout.isRefreshing
                && !refreshLayout.isLoading) {
            showLoading()
        }
    }

    override fun onLoadStop(presenter: IPresenter) {
        refreshLayout.finishRefresh()
        refreshLayout.finishLoadmore()
        showContent()
    }

    override fun setData(results: List<Book>) {
        mList.clear()
        mList.addAll(results)
        mAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mPresenter.unSubscribe()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.bookshelf, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_search -> {
                startActivity(Intent(context, SearchActivity::class.java))
                return true
            }
            R.id.menu_more -> {
                toast("function")
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    companion object {

        fun newInstance(): BookshelfFragment {
            val fragment = BookshelfFragment()
            return fragment
        }
    }
}
