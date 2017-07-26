package com.lnybrave.zzbook.ui.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import com.lnybrave.zzbook.R
import com.lnybrave.zzbook.bean.Book
import com.lnybrave.zzbook.databinding.ActivityMainBinding
import com.lnybrave.zzbook.di.component.DaggerMainComponent
import com.lnybrave.zzbook.di.module.ActivityModule
import com.lnybrave.zzbook.di.module.ApiModule
import com.lnybrave.zzbook.di.module.AppModule
import com.lnybrave.zzbook.di.module.MainModule
import com.lnybrave.zzbook.mvp.contract.BookshelfContract
import com.lnybrave.zzbook.mvp.presenter.BookshelfPresenter
import com.lnybrave.zzbook.ui.fragment.BookshelfFragment
import com.lnybrave.zzbook.ui.fragment.ClassificationFragment
import com.lnybrave.zzbook.ui.fragment.RecommendationFragment
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : BaseBindingActivity<ActivityMainBinding>(), BookshelfContract.View {

    lateinit var mFragments: MutableList<Fragment>
    var selectedPosition: Int = 0
    @Inject lateinit var mPresenter: BookshelfPresenter

    override fun createDataBinding(savedInstanceState: Bundle?): ActivityMainBinding {
        return DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun initView() {
        initFragments()

        viewPager.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int) = mFragments[position]
            override fun getCount() = mFragments.size
        }

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                navigationView.menu.getItem(selectedPosition).isChecked = true
                navigationView.menu.getItem(position).isChecked = true
                selectedPosition = position
            }
        })

        viewPager.offscreenPageLimit = mFragments.size

        navigationView.setOnNavigationItemSelectedListener { item ->
            var tab = 0
            when (item.itemId) {
                R.id.menu_android -> tab = 0
                R.id.menu_ios -> tab = 1
                R.id.menu_girl -> tab = 2
            }
            viewPager.setCurrentItem(tab, false)
            false
        }

        DaggerMainComponent.builder().appModule(AppModule(this.applicationContext))
                .apiModule(ApiModule())
                .mainModule(MainModule(this))
                .activityModule(ActivityModule(this))
                .build()
                .inject(this)
        mPresenter.getData()
    }

    private fun initFragments() {
        mFragments = ArrayList()
        mFragments.add(BookshelfFragment.newInstance())
        mFragments.add(RecommendationFragment.newInstance())
        mFragments.add(ClassificationFragment.newInstance())
    }

    override fun setData(results: List<Book>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
