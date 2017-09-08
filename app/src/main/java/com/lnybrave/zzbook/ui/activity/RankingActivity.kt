package com.lnybrave.zzbook.ui.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
import com.lnybrave.zzbook.R
import com.lnybrave.zzbook.bean.Ranking
import com.lnybrave.zzbook.bean.StackMenu
import com.lnybrave.zzbook.di.component.DaggerMainComponent
import com.lnybrave.zzbook.di.component.MainComponent
import com.lnybrave.zzbook.di.module.ActivityModule
import com.lnybrave.zzbook.di.module.RankingModule
import com.lnybrave.zzbook.getAppComponent
import com.lnybrave.zzbook.mvp.IPresenter
import com.lnybrave.zzbook.mvp.contract.RankingContract
import com.lnybrave.zzbook.mvp.presenter.RankingPresenter
import com.lnybrave.zzbook.ui.ProgressActivity
import com.lnybrave.zzbook.ui.fragment.RankingDetailFragment
import com.malinskiy.materialicons.IconDrawable
import com.malinskiy.materialicons.Iconify
import kotlinx.android.synthetic.main.activity_ranking.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject


class RankingActivity : ProgressActivity(), RankingContract.View {

    lateinit var skipIds: ArrayList<Int>
    lateinit var mainComponent: MainComponent
    @Inject lateinit var mPresenter: RankingPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)
    }

    override fun initView() {
        setupToolbar(toolbar)
        val subject = intent.getSerializableExtra("subject") as StackMenu
        tvTitle.text = subject.name

        skipIds = ArrayList()
        skipIds.add(R.id.rl_toolbar)

        setupProgressWidget(progress)

        mainComponent = DaggerMainComponent.builder()
                .appComponent(getAppComponent())
                .activityModule(ActivityModule(this))
                .build()
        mainComponent.plus(RankingModule(this)).inject(this)
    }

    override fun initData() {
        mPresenter.getData()
    }

    override fun setData(results: List<Ranking>) {
        val fragments: MutableList<Fragment> = ArrayList()
        val titles: MutableList<String> = ArrayList()
        results.mapTo(fragments) { RankingDetailFragment.newInstance(it.id) }
        results.mapTo(titles) { it.name }

        viewPager.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int) = fragments[position]
            override fun getCount() = fragments.size
            override fun getPageTitle(position: Int): CharSequence {
                return titles[position]
            }
        }

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {

            }
        })

        viewPager.offscreenPageLimit = fragments.size

        tab.setupWithViewPager(viewPager)
    }

    override fun onEmpty(presenter: IPresenter) {
        val emptyDrawable = IconDrawable(this, Iconify.IconValue.zmdi_shopping_basket)
                .colorRes(android.R.color.white)

        showEmpty(emptyDrawable,
                "Empty Shopping Cart",
                "Please add things in the cart to continue.",
                skipIds)
    }

    override fun onError(presenter: IPresenter, message: String?) {
        val errorDrawable = IconDrawable(this, Iconify.IconValue.zmdi_wifi_off)
                .colorRes(android.R.color.white)

        showError(errorDrawable,
                "No Connection",
                "We could not establish a connection with our servers. Please try again when you are connected to the internet.",
                "重试",
                View.OnClickListener {
                    initData()
                },
                skipIds)
    }

    override fun onLoadStart(presenter: IPresenter) {
        showLoading(skipIds)
    }

    override fun onLoadStop(presenter: IPresenter) {
        showContent()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.unSubscribe()
    }
}
