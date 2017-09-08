package com.lnybrave.zzbook.ui.activity

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.ActionBarDrawerToggle
import android.view.View
import com.lnybrave.zzbook.R
import com.lnybrave.zzbook.databinding.ActivityMainBinding
import com.lnybrave.zzbook.di.component.DaggerMainComponent
import com.lnybrave.zzbook.di.component.MainComponent
import com.lnybrave.zzbook.di.module.ActivityModule
import com.lnybrave.zzbook.getAppComponent
import com.lnybrave.zzbook.ui.BaseBindingActivity
import com.lnybrave.zzbook.ui.fragment.BookshelfFragment
import com.lnybrave.zzbook.ui.fragment.ClassificationFragment
import com.lnybrave.zzbook.ui.fragment.RecommendationFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_main_menu.*


class MainActivity : BaseBindingActivity<ActivityMainBinding>() {

    lateinit var bookshelfFragment: BookshelfFragment
    lateinit var recommendationFragment: RecommendationFragment
    lateinit var classificationFragment: ClassificationFragment

    lateinit var mFragments: MutableList<Fragment>
    var selectedPosition: Int = 0

    lateinit var mainComponent: MainComponent

    override fun createDataBinding(savedInstanceState: Bundle?): ActivityMainBinding {
        return DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun initView() {
        initTitle()

        initSideMenu()

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
                R.id.menu_search -> tab = 0
                R.id.menu_more -> tab = 1
                R.id.menu_girl -> tab = 2
            }
            viewPager.setCurrentItem(tab, false)
            false
        }

        mainComponent = DaggerMainComponent.builder()
                .appComponent(getAppComponent())
                .activityModule(ActivityModule(this))
                .build()
    }

    private fun initTitle() {
        toolbar.title = ""
        setSupportActionBar(toolbar)

        val toggle = object : ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
            }

        }
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        // 必须在setSupportActionBar和syncState之后，否则无效
        toolbar.setNavigationIcon(R.drawable.ic_nav_my)
    }

    private fun initSideMenu() {
        ll_user_info.setOnClickListener { startActivity(Intent(applicationContext, AccountActivity::class.java)) }
        ll_messages.setOnClickListener { startActivity(Intent(applicationContext, MessagesActivity::class.java)) }
        ll_my_tracks.setOnClickListener { startActivity(Intent(applicationContext, MyTracksActivity::class.java)) }
        ll_feedback.setOnClickListener { startActivity(Intent(applicationContext, FeedbackActivity::class.java)) }
        ll_about_us.setOnClickListener { startActivity(Intent(applicationContext, AboutActivity::class.java)) }
        tv_setting.setOnClickListener { startActivity(Intent(applicationContext, SettingsActivity::class.java)) }
        tv_day_night_mode.setOnClickListener {
            if (tv_day_night_mode.text == resources.getString(R.string.text_day)) {
                tv_day_night_mode.setText(R.string.text_night)
                val drawable = resources.getDrawable(R.drawable.ic_sidemenu_night)
                tv_day_night_mode.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
            } else {
                tv_day_night_mode.setText(R.string.text_day)
                val drawable = resources.getDrawable(R.drawable.ic_sidemenu_day)
                tv_day_night_mode.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
            }
        }
    }

    private fun initFragments() {
        mFragments = ArrayList()
        bookshelfFragment = BookshelfFragment.newInstance()
        recommendationFragment = RecommendationFragment.newInstance()
        classificationFragment = ClassificationFragment.newInstance()
        mFragments.add(bookshelfFragment)
        mFragments.add(recommendationFragment)
        mFragments.add(classificationFragment)
    }
}
