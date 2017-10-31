package com.lnybrave.zzbook.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lnybrave.zzbook.bean.APIPage
import com.lnybrave.zzbook.bean.Book
import com.lnybrave.zzbook.databinding.ViewRecyclerBinding
import com.lnybrave.zzbook.di.module.SearchModule
import com.lnybrave.zzbook.mvp.IPresenter
import com.lnybrave.zzbook.mvp.contract.SearchContract
import com.lnybrave.zzbook.mvp.presenter.SearchPresenter
import com.lnybrave.zzbook.ui.BaseBindingFragment
import com.lnybrave.zzbook.ui.activity.SearchActivity
import com.lnybrave.zzbook.ui.multitype.BookComplexViewBinder
import me.drakeet.multitype.MultiTypeAdapter
import java.util.*
import javax.inject.Inject

class SearchFragment : BaseBindingFragment<ViewRecyclerBinding>(), SearchContract.View {

    private var mList = ArrayList<Any>()
    private lateinit var mAdapter: MultiTypeAdapter
    @Inject lateinit var mPresenter: SearchPresenter

    private var keyword: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            keyword = arguments.getString(SearchFragment.KEYWORD)
        }
    }

    override fun createDataBinding(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): ViewRecyclerBinding {
        return ViewRecyclerBinding.inflate(inflater, container, false)
    }

    override fun initView(view: View?) {
        mAdapter = MultiTypeAdapter(mList)

        with(mBinding) {
            recyclerView.adapter = mAdapter
            recyclerView.layoutManager = LinearLayoutManager(context)

            mAdapter.register(Book::class.java, BookComplexViewBinder())
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity is SearchActivity) {
            val a: SearchActivity = activity as SearchActivity
            a.mainComponent.plus(SearchModule(this)).inject(this)
            mPresenter.getData(keyword)
        } else {
            throw IllegalArgumentException("is not SearchActivity")
        }
    }

    override fun onUserVisible(firstVisible: Boolean) {
        if (firstVisible) {
            mPresenter.getData(keyword)
        }
    }

    override fun onEmpty(presenter: IPresenter) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onError(presenter: IPresenter, message: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setData(results: APIPage<Book>) {
        mList.clear()
        if (results.results.isNotEmpty()) {
            mList.addAll(results.results)
        }
        mAdapter.notifyDataSetChanged()
    }

    override fun onLoadStart(presenter: IPresenter) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onLoadStop(presenter: IPresenter) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mPresenter.unSubscribe()
    }

    companion object {

        private val KEYWORD = "keyword"

        fun newInstance(keyword: String): SearchFragment {
            val fragment = SearchFragment()
            val args: Bundle = Bundle()
            args.putString(KEYWORD, keyword)
            fragment.arguments = args
            return fragment
        }
    }
}
