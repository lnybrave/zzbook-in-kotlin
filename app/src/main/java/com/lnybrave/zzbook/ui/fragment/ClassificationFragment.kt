package com.lnybrave.zzbook.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import com.lnybrave.zzbook.R
import com.lnybrave.zzbook.bean.Classification
import com.lnybrave.zzbook.databinding.ViewRecyclerBinding
import com.lnybrave.zzbook.di.module.ClassificationModule
import com.lnybrave.zzbook.mvp.IPresenter
import com.lnybrave.zzbook.mvp.contract.ClassificationContract
import com.lnybrave.zzbook.mvp.presenter.ClassificationPresenter
import com.lnybrave.zzbook.ui.BaseBindingFragment
import com.lnybrave.zzbook.ui.activity.ClassificationActivity
import com.lnybrave.zzbook.ui.activity.MainActivity
import com.lnybrave.zzbook.ui.activity.SearchActivity
import com.lnybrave.zzbook.ui.multitype.ClassificationFirstViewBinder
import com.lnybrave.zzbook.ui.multitype.ClassificationSecondViewBinder
import com.malinskiy.materialicons.IconDrawable
import com.malinskiy.materialicons.Iconify
import kotlinx.android.synthetic.main.view_recycler.*
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
            setupProgressWidget(progress)

            refreshLayout.isEnableLoadmore = false
            refreshLayout.setOnRefreshListener({ layout -> mPresenter.getData() })

            recyclerView.adapter = mAdapter
            recyclerView.layoutManager = LinearLayoutManager(context)

            mAdapter.register(Classification::class.java)
                    .to(ClassificationFirstViewBinder(), ClassificationSecondViewBinder())
                    .withLinker { classification -> classification.level }
        }
    }

    private fun initTitle() {
        if (activity is MainActivity) {
            setHasOptionsMenu(true)
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity is MainActivity) {
            val a: MainActivity = activity as MainActivity
            a.mainComponent.plus(ClassificationModule(this)).inject(this)
            initData()
        } else if (activity is ClassificationActivity) {
            val a: ClassificationActivity = activity as ClassificationActivity
            a.mainComponent.plus(ClassificationModule(this)).inject(this)
            initData()
        } else {
            throw IllegalArgumentException("is not MainActivity or ClassificationActivity")
        }
    }

    private fun initData() {
        mPresenter.getData()
    }

    override fun setData(results: List<Classification>) {
        refreshLayout.finishRefresh()

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

    override fun onEmpty(presenter: IPresenter) {
        val emptyDrawable = IconDrawable(this.activity, Iconify.IconValue.zmdi_shopping_basket)
                .colorRes(android.R.color.white)

        showEmpty(emptyDrawable,
                "Empty Shopping Cart",
                "Please add things in the cart to continue.")
    }

    override fun onError(presenter: IPresenter, message: String?) {
        val errorDrawable = IconDrawable(this.activity, Iconify.IconValue.zmdi_wifi_off)
                .colorRes(android.R.color.white)

        showError(errorDrawable,
                "No Connection",
                "We could not establish a connection with our servers. Please try again when you are connected to the internet.",
                "重试",
                View.OnClickListener {
                    initData()
                })
    }

    override fun onLoadStart(presenter: IPresenter) {
        if (!refreshLayout.isRefreshing && !refreshLayout.isLoading) {
            showLoading()
        }
    }

    override fun onLoadStop(presenter: IPresenter) {
        refreshLayout.finishRefresh()
        refreshLayout.finishLoadmore()
        showContent()
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
