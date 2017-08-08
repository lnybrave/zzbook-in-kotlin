package com.lnybrave.zzbook.ui.activity

import android.os.Bundle
import com.lnybrave.zzbook.R
import com.lnybrave.zzbook.bean.Subject
import com.lnybrave.zzbook.di.component.DaggerMainComponent
import com.lnybrave.zzbook.di.component.MainComponent
import com.lnybrave.zzbook.di.module.ActivityModule
import com.lnybrave.zzbook.getAppComponent
import com.lnybrave.zzbook.ui.BaseActivity
import com.lnybrave.zzbook.ui.fragment.ClassificationFragment
import kotlinx.android.synthetic.main.toolbar.*

class ClassificationActivity : BaseActivity() {

    lateinit var mainComponent: MainComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_classification)
        initView()
    }

    override fun initView() {
        setupToolbar(toolbar)
        val subject = intent.getSerializableExtra("subject") as Subject
        tvTitle.text = subject.name

        mainComponent = DaggerMainComponent.builder()
                .appComponent(getAppComponent())
                .activityModule(ActivityModule(this))
                .build()

        if (!isFinishing) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment, ClassificationFragment.newInstance())
                    .commitAllowingStateLoss()
        }
    }
}
