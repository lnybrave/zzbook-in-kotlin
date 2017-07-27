package com.lnybrave.zzbook.di.component

import com.lnybrave.zzbook.di.module.ActivityModule
import com.lnybrave.zzbook.di.module.BookshelfModule
import com.lnybrave.zzbook.di.scope.PerActivity
import com.lnybrave.zzbook.ui.fragment.BookshelfFragment
import dagger.Component


@PerActivity
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(ActivityModule::class, BookshelfModule::class))
interface MainComponent : ActivityComponent {

    fun inject(fragment: BookshelfFragment)
}

