package com.lnybrave.zzbook.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lnybrave.zzbook.bean.Ranking
import com.lnybrave.zzbook.databinding.ViewRecyclerBinding
import com.lnybrave.zzbook.di.component.RankingModule
import com.lnybrave.zzbook.mvp.contract.RankingContract
import com.lnybrave.zzbook.mvp.presenter.RankingPresenter
import com.lnybrave.zzbook.ui.activity.RankingActivity
import com.lnybrave.zzbook.ui.adapter.RankingAdapter
import java.util.*
import javax.inject.Inject

class RankingFragment : BaseBindingFragment<ViewRecyclerBinding>(), RankingContract.View {

    private var mList = ArrayList<Ranking>()
    private lateinit var mAdapter: RankingAdapter
    @Inject lateinit var mPresenter: RankingPresenter


    override fun createDataBinding(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): ViewRecyclerBinding {
        return ViewRecyclerBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        mAdapter = RankingAdapter(mList)

        with(mBinding) {
            recyclerView.adapter = mAdapter
            recyclerView.layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity is RankingActivity) {
            val a: RankingActivity = activity as RankingActivity
            a.mainComponent.plus(RankingModule(this)).inject(this)
            mPresenter.getData()
        } else {
            throw IllegalArgumentException("is not MainActivity")
        }
    }

    override fun setData(results: List<Ranking>) {
        mList.clear()
        mList.addAll(results)
        mAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mPresenter.unSubscribe()
    }

    companion object {

        fun newInstance(param1: String, param2: String): RankingFragment {
            val fragment = RankingFragment()
            return fragment
        }
    }
}
