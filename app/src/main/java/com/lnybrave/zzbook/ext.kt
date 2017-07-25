package com.lnybrave.zzbook

import android.content.Context
import android.widget.Toast


fun Context.getMainComponent() = App.instance.apiComponent

fun Context.toast(msg: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, msg, length).show()
}