package com.lnybrave.zzbook.di.component

import com.lnybrave.zzbook.di.module.ActivityModule
import com.lnybrave.zzbook.di.module.ApiModule
import com.lnybrave.zzbook.di.module.MainModule
import com.lnybrave.zzbook.di.scope.PerActivity
import com.lnybrave.zzbook.ui.activity.MainActivity
import dagger.Component


@PerActivity
@Component(modules = arrayOf(MainModule::class, ActivityModule::class, ApiModule::class))
interface MainComponent : ActivityComponent {

    fun inject(mainActivity: MainActivity)

    fun plus(): MainFragmentComponent
}

