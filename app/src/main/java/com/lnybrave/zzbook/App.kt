package com.lnybrave.zzbook

import android.app.Application
import com.lnybrave.zzbook.di.module.ApiModule
import com.lnybrave.zzbook.di.module.AppModule
import com.wingsofts.gankclient.di.component.ApiComponent
import com.wingsofts.gankclient.di.component.DaggerApiComponent
import javax.inject.Inject


class App : Application() {

    init {
        instance = this
    }

    @Inject lateinit var apiComponent: ApiComponent

    override fun onCreate() {
        super.onCreate()

        DaggerApiComponent.builder()
                .apiModule(ApiModule())
                .appModule(AppModule(this))
                .build()
                .inject(this)
    }

    companion object {
        lateinit var instance: App
    }
}