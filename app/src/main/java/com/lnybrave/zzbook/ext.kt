package com.lnybrave.zzbook

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.lnybrave.zzbook.di.component.DaggerActivityComponent
import com.lnybrave.zzbook.di.module.ActivityModule


fun Context.getAppComponent() = App.instance.appComponent

fun AppCompatActivity.getActivityComponent() = DaggerActivityComponent.builder()
        .appComponent(getAppComponent())
        .activityModule(getActivityModule())
        .build()

fun AppCompatActivity.getActivityModule() = ActivityModule(this)

fun Context.toast(msg: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, msg, length).show()
}

fun Fragment.toast(msg: String, length: Int = Toast.LENGTH_SHORT) {
    if (activity != null)
        Toast.makeText(activity, msg, length).show()
}