package com.lnybrave.zzbook.di.component

import android.content.Context
import com.lnybrave.zzbook.di.module.AppModule
import dagger.Component

@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun getContext(): Context
}

