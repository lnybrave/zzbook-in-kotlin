package com.lnybrave.zzbook.di.component

import com.lnybrave.zzbook.di.module.ActivityModule
import com.lnybrave.zzbook.di.module.BookshelfModule
import com.lnybrave.zzbook.di.scope.PerActivity
import com.lnybrave.zzbook.ui.activity.MainActivity
import dagger.Component


@PerActivity
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(ActivityModule::class))
interface MainComponent : ActivityComponent {

    fun inject(mainActivity: MainActivity)

    fun plus(bookshelfModule: BookshelfModule) : BookshelfComponent
}

