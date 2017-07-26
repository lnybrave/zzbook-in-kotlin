package com.lnybrave.zzbook

import android.app.Application
import com.lnybrave.zzbook.di.component.AppComponent
import com.lnybrave.zzbook.di.component.DaggerAppComponent
import com.lnybrave.zzbook.di.module.AppModule
import javax.inject.Inject


class App : Application() {

    init {
        instance = this
    }

    @Inject lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    companion object {
        lateinit var instance: App
    }
}