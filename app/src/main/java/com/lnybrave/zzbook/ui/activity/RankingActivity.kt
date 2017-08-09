package com.lnybrave.zzbook.ui.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import com.lnybrave.zzbook.R
import com.lnybrave.zzbook.bean.Ranking
import com.lnybrave.zzbook.bean.Subject
import com.lnybrave.zzbook.di.component.DaggerMainComponent
import com.lnybrave.zzbook.di.component.MainComponent
import com.lnybrave.zzbook.di.module.RankingModule
import com.lnybrave.zzbook.di.module.ActivityModule
import com.lnybrave.zzbook.getAppComponent
import com.lnybrave.zzbook.mvp.contract.RankingContract
import com.lnybrave.zzbook.mvp.presenter.RankingPresenter
import com.lnybrave.zzbook.ui.BaseActivity
import com.lnybrave.zzbook.ui.fragment.RankingDetailFragment
import kotlinx.android.synthetic.main.activity_ranking.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

class RankingActivity : BaseActivity(), RankingContract.View {

    lateinit var mainComponent: MainComponent
    @Inject lateinit var mPresenter: RankingPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)
        initView()
        initData()
    }

    override fun initView() {
        setupToolbar(toolbar)
        val subject = intent.getSerializableExtra("subject") as Subject
        tvTitle.text = subject.name

        mainComponent = DaggerMainComponent.builder()
                .appComponent(getAppComponent())
                .activityModule(ActivityModule(this))
                .build()
    }

    fun initData() {
        mainComponent.plus(RankingModule(this)).inject(this)
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

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.unSubscribe()
    }
}
