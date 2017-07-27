package com.lnybrave.zzbook.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lnybrave.zzbook.bean.Classification
import com.lnybrave.zzbook.databinding.ViewRecyclerBinding
import com.lnybrave.zzbook.di.component.ClassificationModule
import com.lnybrave.zzbook.mvp.contract.ClassificationContract
import com.lnybrave.zzbook.mvp.presenter.ClassificationPresenter
import com.lnybrave.zzbook.ui.activity.MainActivity
import com.lnybrave.zzbook.ui.adapter.ClassificationAdapter
import java.util.*
import javax.inject.Inject


class ClassificationFragment : BaseBindingFragment<ViewRecyclerBinding>(), ClassificationContract.View {

    private var mList = ArrayList<Classification>()
    private lateinit var mAdapter: ClassificationAdapter
    @Inject lateinit var mPresenter: ClassificationPresenter


    override fun createDataBinding(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): ViewRecyclerBinding {
        return ViewRecyclerBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        mAdapter = ClassificationAdapter(mList)

        with(mBinding) {
            recyclerView.adapter = mAdapter
            recyclerView.layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity is MainActivity) {
            val a: MainActivity = activity as MainActivity
            a.mainComponent.plus(ClassificationModule(this)).inject(this)
            mPresenter.getData()
        } else {
            throw IllegalArgumentException("is not MainActivity")
        }
    }

    override fun setData(results: List<Classification>) {
        mList.clear()
        mList.addAll(results)
        mAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mPresenter.unSubscribe()
    }

    companion object {

        fun newInstance(): ClassificationFragment {
            val fragment = ClassificationFragment()
            return fragment
        }
    }
}
