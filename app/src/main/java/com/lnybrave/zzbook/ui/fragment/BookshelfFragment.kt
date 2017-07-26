package com.lnybrave.zzbook.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.ViewGroup
import com.lnybrave.zzbook.bean.Book
import com.lnybrave.zzbook.databinding.ViewRecyclerBinding
import com.lnybrave.zzbook.mvp.contract.BookshelfContract
import com.lnybrave.zzbook.toast
import com.lnybrave.zzbook.ui.adapter.BookshelfAdapter
import java.util.*


class BookshelfFragment : BaseBindingFragment<ViewRecyclerBinding>(), BookshelfContract.View {


    private var mList = ArrayList<Book>()
    private lateinit var mAdapter: BookshelfAdapter
    //lateinit var mPresenter: BookshelfPresenter

    override fun createDataBinding(inflater: LayoutInflater?, container: ViewGroup?,
                                   savedInstanceState: Bundle?): ViewRecyclerBinding {
        return ViewRecyclerBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        mAdapter = BookshelfAdapter(mList)

        with(mBinding) {
            recyclerView.adapter = mAdapter
            recyclerView.layoutManager = LinearLayoutManager(context)
        }

        //mPresenter.getData()
    }

    override fun setData(results: List<Book>) {
        mList.clear()
        mList.addAll(results)
        mAdapter.notifyDataSetChanged()
        toast("size=" + results.size)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //mPresenter.unSubscribe()
    }

    companion object {

        fun newInstance(): BookshelfFragment {
            val fragment = BookshelfFragment()
            return fragment
        }
    }
}
