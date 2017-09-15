package com.lnybrave.zzbook.ui

import android.app.ProgressDialog
import android.content.DialogInterface
import android.support.v4.app.Fragment

abstract class BaseFragment : Fragment() {

    private var progressDialog: ProgressDialog? = null

    abstract fun initView()


    fun showProgressDialog() {
        showProgressDialog("请稍后")
    }

    fun showProgressDialog(message: String, cancelable: Boolean = false, cancelListener: DialogInterface.OnCancelListener? = null) {
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