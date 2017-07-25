package com.wingsofts.gankclient.di.component

import com.lnybrave.zzbook.App
import com.lnybrave.zzbook.di.module.ApiModule
import dagger.Component

@Component(modules = arrayOf(ApiModule::class))
interface ApiComponent {

    fun inject(app: App)

    fun plus(module: BookshelfModule): BookshelfComponent
}

