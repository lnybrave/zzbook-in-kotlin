package com.lnybrave.zzbook.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import com.lnybrave.zzbook.R
import com.lnybrave.zzbook.bean.Classification
import com.lnybrave.zzbook.databinding.ViewRecyclerBinding
import com.lnybrave.zzbook.di.module.ClassificationModule
import com.lnybrave.zzbook.mvp.contract.ClassificationContract
import com.lnybrave.zzbook.mvp.presenter.ClassificationPresenter
import com.lnybrave.zzbook.ui.BaseBindingFragment
import com.lnybrave.zzbook.ui.activity.ClassificationActivity
import com.lnybrave.zzbook.ui.activity.MainActivity
import com.lnybrave.zzbook.ui.activity.SearchActivity
import com.lnybrave.zzbook.ui.multitype.ClassificationFirstViewBinder
import com.lnybrave.zzbook.ui.multitype.ClassificationSecondViewBinder
import me.drakeet.multitype.MultiTypeAdapter
import java.util.*
import javax.inject.Inject


class ClassificationFragment : BaseBindingFragment<ViewRecyclerBinding>(), ClassificationContract.View {

    private var mList = ArrayList<Any>()
    private lateinit var mAdapter: MultiTypeAdapter
    @Inject lateinit var mPresenter: ClassificationPresenter


    override fun createDataBinding(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): ViewRecyclerBinding {
        return ViewRecyclerBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        initTitle()

        mAdapter = MultiTypeAdapter(mList)

        with(mBinding) {
            recyclerView.adapter = mAdapter
            recyclerView.layoutManager = LinearLayoutManager(context)

            mAdapter.register(Classification::class.java)
                    .to(ClassificationFirstViewBinder(), ClassificationSecondViewBinder())
                    .withLinker { classification -> classification.level }
        }
    }

    private fun initTitle() {
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity is MainActivity) {
            val a: MainActivity = activity as MainActivity
            a.mainComponent.plus(ClassificationModule(this)).inject(this)
            mPresenter.getData()
        } else if (activity is ClassificationActivity) {
            val a: ClassificationActivity = activity as ClassificationActivity
            a.mainComponent.plus(ClassificationModule(this)).inject(this)
            mPresenter.getData()
        } else {
            throw IllegalArgumentException("is not MainActivity")
        }
    }

    override fun setData(results: List<Classification>) {
        mList.clear()
        for (item in results) {
            item.level = 0
            mList.add(item)
            for (c in item.children) {
                c.level = 1
            }
            mList.addAll(item.children)
        }
        mAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mPresenter.unSubscribe()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_search -> {
                startActivity(Intent(context, SearchActivity::class.java))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {

        fun newInstance(): ClassificationFragment {
            val fragment = ClassificationFragment()
            return fragment
        }
    }
}
