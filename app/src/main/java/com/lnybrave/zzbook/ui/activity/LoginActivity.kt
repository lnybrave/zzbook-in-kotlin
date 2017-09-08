package com.lnybrave.zzbook.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.lnybrave.zzbook.R
import com.lnybrave.zzbook.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun initView() {
        tv_forget_password.setOnClickListener {
            startActivity(Intent(this, RetrievePasswordActivity::class.java))
        }
        tv_create_account.setOnClickListener {
            ll_login.visibility = View.GONE
            ll_register.visibility = View.VISIBLE
        }
        tv_have_account.setOnClickListener {
            ll_login.visibility = View.VISIBLE
            ll_register.visibility = View.GONE
        }
        btn_login.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        btn_register.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
