package com.lnybrave.zzbook.di.component

import com.lnybrave.zzbook.di.module.ApiModule
import com.lnybrave.zzbook.di.module.MainModule
import dagger.Component

@Component(modules = arrayOf(ApiModule::class))
interface ApiComponent {

}

