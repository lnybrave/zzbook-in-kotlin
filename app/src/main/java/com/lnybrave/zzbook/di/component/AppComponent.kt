package com.lnybrave.zzbook.di.component

import android.content.Context
import com.lnybrave.zzbook.App
import com.lnybrave.zzbook.api.ZZBookApi
import com.lnybrave.zzbook.di.module.ApiModule
import com.lnybrave.zzbook.di.module.AppModule
import dagger.Component

@Component(modules = arrayOf(AppModule::class, ApiModule::class))
interface AppComponent {

    fun inject(app: App)

    fun getContext(): Context

    fun getApi(): ZZBookApi
}

