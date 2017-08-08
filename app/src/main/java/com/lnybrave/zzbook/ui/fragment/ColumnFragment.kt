package com.lnybrave.zzbook.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lnybrave.zzbook.Constants
import com.lnybrave.zzbook.bean.Book
import com.lnybrave.zzbook.bean.Topic
import com.lnybrave.zzbook.databinding.ViewRecyclerBinding
import com.lnybrave.zzbook.di.module.ColumnModule
import com.lnybrave.zzbook.mvp.contract.ColumnDetailContract
import com.lnybrave.zzbook.mvp.presenter.ColumnDetailPresenter
import com.lnybrave.zzbook.ui.BaseBindingFragment
import com.lnybrave.zzbook.ui.activity.ColumnActivity
import com.lnybrave.zzbook.ui.multitype.BookComplexViewBinder
import com.lnybrave.zzbook.ui.multitype.BookSimpleViewBinder
import com.lnybrave.zzbook.ui.multitype.TopicTitleViewBinder
import me.drakeet.multitype.MultiTypeAdapter
import java.util.*
import javax.inject.Inject


class ColumnFragment : BaseBindingFragment<ViewRecyclerBinding>(), ColumnDetailContract.View {

    private var columnId: Int? = null
    private var mList = ArrayList<Any>()
    private lateinit var mAdapter: MultiTypeAdapter
    @Inject lateinit var mPresenter: ColumnDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            columnId = arguments.getInt(ColumnFragment.COLUMN_ID)
        }
    }

    override fun createDataBinding(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): ViewRecyclerBinding {
        return ViewRecyclerBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        mAdapter = MultiTypeAdapter(mList)

        with(mBinding) {
            recyclerView.adapter = mAdapter
            recyclerView.layoutManager = LinearLayoutManager(context)

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
            a.mainComponent.plus(ColumnModule(this)).inject(this)
            mPresenter.getData(columnId!!)
        } else {
            throw IllegalArgumentException("is not ColumnActivity")
        }
    }

    override fun setData(results: List<Topic>) {
        mList.clear()
        for (topic in results) {
            mList.add(topic)
            for (book in topic.books) {
                book.showType = if (topic.type == Constants.TOPIC_SIMPLE) 0 else 1
            }
            mList.addAll(topic.books)
        }
        mAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mPresenter.unSubscribe()
    }

    companion object {

        private val COLUMN_ID = "column_id"

        fun newInstance(columnId: Int): ColumnFragment {
            val fragment = ColumnFragment()
            val args = Bundle()
            args.putInt(COLUMN_ID, columnId)
            fragment.arguments = args
            return fragment
        }
    }
}
