package com.lnybrave.zzbook.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lnybrave.zzbook.bean.Book
import com.lnybrave.zzbook.bean.Ranking
import com.lnybrave.zzbook.databinding.ViewRecyclerBinding
import com.lnybrave.zzbook.di.module.RankingDetailModule
import com.lnybrave.zzbook.mvp.contract.RankingDetailContract
import com.lnybrave.zzbook.mvp.presenter.RankingDetailPresenter
import com.lnybrave.zzbook.ui.BaseBindingFragment
import com.lnybrave.zzbook.ui.activity.RankingActivity
import com.lnybrave.zzbook.ui.multitype.BookComplexViewBinder
import com.lnybrave.zzbook.ui.multitype.RankingTitleViewBinder
import me.drakeet.multitype.MultiTypeAdapter
import java.util.*
import javax.inject.Inject


class RankingDetailFragment : BaseBindingFragment<ViewRecyclerBinding>(), RankingDetailContract.View {

    private var mList = ArrayList<Any>()
    private lateinit var mAdapter: MultiTypeAdapter
    @Inject lateinit var mPresenter: RankingDetailPresenter

    private var rankingId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            rankingId = arguments.getInt(RankingDetailFragment.ID)
        }
    }

    override fun createDataBinding(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): ViewRecyclerBinding {
        return ViewRecyclerBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        mAdapter = MultiTypeAdapter(mList)

        with(mBinding) {
            refreshLayout.setOnRefreshListener({ layout -> layout.finishRefresh(2000) })
            refreshLayout.setOnLoadmoreListener({ layout -> layout.finishLoadmore(2000) })

            recyclerView.adapter = mAdapter
            recyclerView.layoutManager = LinearLayoutManager(context)

            mAdapter.register(Ranking::class.java, RankingTitleViewBinder())
            mAdapter.register(Book::class.java, BookComplexViewBinder())
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity is RankingActivity) {
            val a: RankingActivity = activity as RankingActivity
            a.mainComponent.plus(RankingDetailModule(this)).inject(this)
            mPresenter.getData(rankingId, 10)
        } else {
            throw IllegalArgumentException("is not RankingActivity")
        }
    }

    override fun setData(results: Ranking) {
        mList.clear()
        if (results.children.isNotEmpty()) {
            addRanking(mList, results.children)
        }
        mAdapter.notifyDataSetChanged()
    }

    private fun addRanking(list: ArrayList<Any>, results: List<Ranking>) {
        for (ranking in results) {
            list.add(ranking)
            list.addAll(ranking.books)
            if (ranking.children.isNotEmpty()) {
                addRanking(mList, ranking.children)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mPresenter.unSubscribe()
    }

    companion object {

        private val ID = "ranking_id"

        fun newInstance(id: Int): RankingDetailFragment {
            val fragment = RankingDetailFragment()
            val args: Bundle = Bundle()
            args.putInt(ID, id)
            fragment.arguments = args
            return fragment
        }
    }
}
