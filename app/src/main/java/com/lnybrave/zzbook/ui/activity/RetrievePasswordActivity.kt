package com.lnybrave.zzbook.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.lnybrave.zzbook.R
import com.lnybrave.zzbook.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_retrieve_password.*
import kotlinx.android.synthetic.main.toolbar.*

class RetrievePasswordActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrieve_password)
    }

    override fun initView() {
        setupToolbar(toolbar)
        tvTitle.text = resources.getString(R.string.title_retrieve_password)

        btn_send_verify.setOnClickListener {
            val intent = Intent(this, RetrievePasswordActivity::class.java)
            intent.putExtra("step", 2)
            startActivity(intent)
        }
        btn_confirm.setOnClickListener {
            val intent = Intent(this, RetrievePasswordActivity::class.java)
            intent.putExtra("step", 3)
            startActivity(intent)
        }
        btn_reset_password.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val value = intent?.getIntExtra("step", 1)
        when (value) {
            1 -> {
                ll_step1.visibility = View.VISIBLE
                ll_step2.visibility = View.GONE
                ll_step3.visibility = View.GONE
            }
            2 -> {
                ll_step1.visibility = View.GONE
                ll_step2.visibility = View.VISIBLE
                ll_step3.visibility = View.GONE
            }
            3 -> {
                ll_step1.visibility = View.GONE
                ll_step2.visibility = View.GONE
                ll_step3.visibility = View.VISIBLE
            }
        }
    }
}
