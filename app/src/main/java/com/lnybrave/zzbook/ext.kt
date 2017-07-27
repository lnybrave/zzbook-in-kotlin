package com.lnybrave.zzbook

import android.content.Context
import android.support.v4.app.Fragment
import android.widget.Toast


fun Context.getAppComponent() = App.instance.appComponent

fun Context.toast(msg: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, msg, length).show()
}

fun Fragment.toast(msg: String, length: Int = Toast.LENGTH_SHORT) {
    if (activity != null)
        Toast.makeText(activity, msg, length).show()
}