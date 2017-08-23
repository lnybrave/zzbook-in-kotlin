package com.lnybrave.zzbook.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lnybrave.zzbook.bean.APIPage
import com.lnybrave.zzbook.bean.Book
import com.lnybrave.zzbook.databinding.ViewRecyclerBinding
import com.lnybrave.zzbook.di.module.ClassificationDetailModule
import com.lnybrave.zzbook.mvp.contract.ClassificationDetailContract
import com.lnybrave.zzbook.mvp.presenter.ClassificationDetailPresenter
import com.lnybrave.zzbook.ui.BaseBindingFragment
import com.lnybrave.zzbook.ui.activity.ClassificationDetailActivity
import com.lnybrave.zzbook.ui.multitype.BookComplexViewBinder
import kotlinx.android.synthetic.main.view_recycler.*
import me.drakeet.multitype.MultiTypeAdapter
import java.util.*
import javax.inject.Inject

class ClassificationDetailFragment : BaseBindingFragment<ViewRecyclerBinding>(), ClassificationDetailContract.View {

    private var mList = ArrayList<Any>()
    private lateinit var mAdapter: MultiTypeAdapter
    @Inject lateinit var mPresenter: ClassificationDetailPresenter

    private var firstId: Int = 0
    private var secondId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            firstId = arguments.getInt(ClassificationDetailFragment.FIRST_ID)
            secondId = arguments.getInt(ClassificationDetailFragment.SECOND_ID)
        }
    }

    override fun createDataBinding(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): ViewRecyclerBinding {
        return ViewRecyclerBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        mAdapter = MultiTypeAdapter(mList)

        with(mBinding) {
            refreshLayout.isEnableRefresh = false
            refreshLayout.setOnLoadmoreListener { layout -> getData() }

            recyclerView.adapter = mAdapter
            recyclerView.layoutManager = LinearLayoutManager(context)

            mAdapter.register(Book::class.java, BookComplexViewBinder())
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity is ClassificationDetailActivity) {
            val a: ClassificationDetailActivity = activity as ClassificationDetailActivity
            a.mainComponent.plus(ClassificationDetailModule(this)).inject(this)
            getData()
        } else {
            throw IllegalArgumentException("is not ClassificationDetailActivity")
        }
    }

    private fun getData() {
        mPresenter.getData(firstId, secondId, mList.size)
    }

    override fun setData(page: APIPage<Book>) {
        refreshLayout.finishLoadmore()
        if (!page.hasNext()) {
            refreshLayout.isEnableLoadmore = false
        }

        if (page.results.isNotEmpty()) {
            mList.addAll(page.results)
        }

        mAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mPresenter.unSubscribe()
    }

    companion object {

        private val FIRST_ID = "first_id"
        private val SECOND_ID = "second_id"

        fun newInstance(firstId: Int, secondId: Int): ClassificationDetailFragment {
            val fragment = ClassificationDetailFragment()
            val args: Bundle = Bundle()
            args.putInt(FIRST_ID, firstId)
            args.putInt(SECOND_ID, secondId)
            fragment.arguments = args
            return fragment
        }
    }
}
