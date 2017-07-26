package com.lnybrave.zzbook.di.component

import android.app.Activity
import com.lnybrave.zzbook.di.module.ActivityModule
import com.lnybrave.zzbook.di.scope.PerActivity
import dagger.Component

@PerActivity
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun getActivity(): Activity
}