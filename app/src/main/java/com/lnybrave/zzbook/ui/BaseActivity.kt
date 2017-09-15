package com.lnybrave.zzbook.ui

import android.app.ProgressDialog
import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem

abstract class BaseActivity : AppCompatActivity() {

    private var progressDialog: ProgressDialog? = null

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        initView()
    }

    abstract fun initView()

    override fun onStart() {
        super.onStart()
        initData()
    }

    open fun initData() {

    }

    fun setupToolbar(toolbar: Toolbar) {
        toolbar.title = ""
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
    }


    fun showProgressDialog() {
        showProgressDialog("请稍后")
    }

    fun showProgressDialog(message: String, cancelable: Boolean = false, cancelListener: DialogInterface.OnCancelListener? = null) {
        if (progressDialog == null) {
            progressDialog = ProgressDialog(this)
        }

        progressDialog!!.setMessage(message)
        progressDialog!!.setCancelable(cancelable)
        if (cancelable && cancelListener != null) {
            progressDialog!!.setOnCancelListener(cancelListener)
        }

        if (!progressDialog?.isShowing!!) {
            progressDialog?.show()
        }
    }

    fun hideProgressDialog() {
        progressDialog?.dismiss()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}