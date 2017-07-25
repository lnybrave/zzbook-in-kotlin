package com.lnybrave.zzbook.ui.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import com.lnybrave.zzbook.R
import com.lnybrave.zzbook.databinding.ActivityMainBinding
import com.lnybrave.zzbook.ui.fragment.BookshelfFragment
import com.lnybrave.zzbook.ui.fragment.ClassificationFragment
import com.lnybrave.zzbook.ui.fragment.MineFragment
import com.lnybrave.zzbook.ui.fragment.RecommendationFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseBindingActivity<ActivityMainBinding>() {

    lateinit var mFragments: MutableList<Fragment>


    override fun createDataBinding(savedInstanceState: Bundle?): ActivityMainBinding {
        return DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun initView() {
        initFragments()

        viewPager.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int) = mFragments[position]
            override fun getCount() = mFragments.size
        }

        viewPager.offscreenPageLimit = 4

        navigationView.setOnNavigationItemSelectedListener { item ->
            var tab = 0
            when (item.itemId) {
                R.id.menu_android -> tab = 0
                R.id.menu_ios -> tab = 1
                R.id.menu_girl -> tab = 2
                R.id.menu_about -> tab = 3
            }
            viewPager.currentItem = tab
            false
        }
    }

    private fun initFragments() {
        mFragments = ArrayList()
        mFragments.add(BookshelfFragment.newInstance())
        mFragments.add(RecommendationFragment.newInstance())
        mFragments.add(ClassificationFragment.newInstance())
        mFragments.add(MineFragment())
    }
}
