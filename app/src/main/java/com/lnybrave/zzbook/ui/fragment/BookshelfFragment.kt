package com.lnybrave.zzbook.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.ViewGroup
import com.lnybrave.zzbook.bean.Book
import com.lnybrave.zzbook.databinding.ViewRecyclerBinding
import com.lnybrave.zzbook.mvp.contract.BookshelfContract
import com.lnybrave.zzbook.mvp.presenter.BookshelfPresenter
import com.lnybrave.zzbook.ui.adapter.BookshelfAdapter
import com.lnybrave.zzbook.getMainComponent
import com.wingsofts.gankclient.di.component.BookshelfModule
import java.util.*
import javax.inject.Inject

class BookshelfFragment : BaseBindingFragment<ViewRecyclerBinding>(), BookshelfContract.View {


    private var mList = ArrayList<Book>()
    private lateinit var mAdapter: BookshelfAdapter
    @Inject lateinit var mPresenter: BookshelfPresenter

    override fun createDataBinding(inflater: LayoutInflater?, container: ViewGroup?,
                                   savedInstanceState: Bundle?): ViewRecyclerBinding {
        return ViewRecyclerBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        mAdapter = BookshelfAdapter(mList)

        context.getMainComponent().plus(BookshelfModule(this)).inject(this)

        with(mBinding) {
            recyclerView.adapter = mAdapter
            recyclerView.layoutManager = LinearLayoutManager(context)
        }

        mPresenter.getData()
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

    companion object {

        fun newInstance(): BookshelfFragment {
            val fragment = BookshelfFragment()
            return fragment
        }
    }
}
