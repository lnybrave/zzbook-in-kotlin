package com.lnybrave.zzbook.ui

import android.app.ProgressDialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View

abstract class BaseFragment : Fragment() {

    private var progressDialog: ProgressDialog? = null

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    abstract fun initView(view: View?)

    fun showProgressDialog() {
        showProgressDialog("请稍后...")
    }

    fun showProgressDialog(message: String,
                                     cancelable: Boolean = false,
                                     cancelListener: DialogInterface.OnCancelListener? = null) {
        if (progressDialog == null && activity != null) {
            progressDialog = ProgressDialog(activity)
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

}