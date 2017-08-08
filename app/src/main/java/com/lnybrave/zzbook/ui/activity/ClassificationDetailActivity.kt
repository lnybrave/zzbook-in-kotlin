package com.lnybrave.zzbook.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.lnybrave.zzbook.R
import com.lnybrave.zzbook.bean.Classification
import com.lnybrave.zzbook.di.component.DaggerMainComponent
import com.lnybrave.zzbook.di.component.MainComponent
import com.lnybrave.zzbook.di.module.ActivityModule
import com.lnybrave.zzbook.getAppComponent
import com.lnybrave.zzbook.ui.BaseActivity
import com.lnybrave.zzbook.ui.fragment.ClassificationDetailFragment
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import kotlinx.android.synthetic.main.activity_classification_detail.*
import kotlinx.android.synthetic.main.toolbar.*

class ClassificationDetailActivity : BaseActivity() {

    lateinit var mainComponent: MainComponent
    lateinit var classification: Classification

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_classification_detail)
        initView()
    }

    override fun initView() {
        setupToolbar(toolbar)
        classification = intent.getSerializableExtra("classification") as Classification
        tvTitle.text = classification.name

        mainComponent = DaggerMainComponent.builder()
                .appComponent(getAppComponent())
                .activityModule(ActivityModule(this))
                .build()

        val results: List<Classification> = classification.children
        if (results is ArrayList) {
            results.add(Classification(0, "全部"))
        }
        tagFlowLayout.adapter = object : TagAdapter<Classification>(results) {
            override fun getView(parent: FlowLayout, position: Int, s: Classification): View {
                val tv = LayoutInflater.from(applicationContext).inflate(R.layout.item_search_word, tagFlowLayout, false) as TextView
                tv.text = s.name
                return tv
            }
        }
        tagFlowLayout.setOnTagClickListener { _, position, _ -> refreshList(results[position]) }

        if (results.isNotEmpty()) {
            tagFlowLayout.adapter.setSelected(0, results[0])
            refreshList(results[0])
        }
    }

    private fun refreshList(second: Classification): Boolean {
        if (!isFinishing) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment, ClassificationDetailFragment.newInstance(classification.id, second.id))
                    .commitAllowingStateLoss()
            return true
        }
        return false
    }
}
