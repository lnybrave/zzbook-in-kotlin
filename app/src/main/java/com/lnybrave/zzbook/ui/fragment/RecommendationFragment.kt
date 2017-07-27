package com.lnybrave.zzbook.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lnybrave.zzbook.bean.Topic
import com.lnybrave.zzbook.databinding.ViewRecyclerBinding
import com.lnybrave.zzbook.di.component.RecommendationModule
import com.lnybrave.zzbook.mvp.contract.RecommendationContract
import com.lnybrave.zzbook.mvp.presenter.RecommendationPresenter
import com.lnybrave.zzbook.ui.activity.MainActivity
import javax.inject.Inject


class RecommendationFragment : BaseBindingFragment<ViewRecyclerBinding>(), RecommendationContract.View {


    @Inject lateinit var mPresenter: RecommendationPresenter


    override fun createDataBinding(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): ViewRecyclerBinding {
        return ViewRecyclerBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity is MainActivity) {
            val a: MainActivity = activity as MainActivity
            a.mainComponent.plus(RecommendationModule(this)).inject(this)
            mPresenter.getData()
        } else {
            throw IllegalArgumentException("is not MainActivity")
        }
    }

    override fun setData(results: List<Topic>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mPresenter.unSubscribe()
    }

    companion object {

        fun newInstance(): RecommendationFragment {
            val fragment = RecommendationFragment()
            return fragment
        }
    }
}
