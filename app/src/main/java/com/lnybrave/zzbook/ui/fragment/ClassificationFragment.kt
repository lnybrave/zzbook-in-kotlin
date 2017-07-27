package com.lnybrave.zzbook.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lnybrave.zzbook.bean.Classification
import com.lnybrave.zzbook.databinding.ViewRecyclerBinding
import com.lnybrave.zzbook.di.component.ClassificationModule
import com.lnybrave.zzbook.mvp.contract.ClassificationContract
import com.lnybrave.zzbook.mvp.presenter.ClassificationPresenter
import com.lnybrave.zzbook.ui.activity.MainActivity
import javax.inject.Inject


class ClassificationFragment : BaseBindingFragment<ViewRecyclerBinding>(), ClassificationContract.View {


    @Inject lateinit var mPresenter: ClassificationPresenter


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
            a.mainComponent.plus(ClassificationModule(this)).inject(this)
            mPresenter.getData()
        } else {
            throw IllegalArgumentException("is not MainActivity")
        }
    }

    override fun setData(results: List<Classification>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
