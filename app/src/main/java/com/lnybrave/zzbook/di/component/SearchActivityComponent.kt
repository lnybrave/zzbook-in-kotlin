package com.lnybrave.zzbook.di.component

import com.lnybrave.zzbook.di.module.ActivityModule
import com.lnybrave.zzbook.di.module.SearchModule
import com.lnybrave.zzbook.di.module.SearchSuggestModule
import com.lnybrave.zzbook.di.module.SearchWordModule
import com.lnybrave.zzbook.di.scope.PerActivity
import com.lnybrave.zzbook.ui.activity.SearchActivity
import dagger.Component


@PerActivity
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(ActivityModule::class, SearchWordModule::class, SearchSuggestModule::class))
interface SearchActivityComponent : ActivityComponent {
    fun inject(activity: SearchActivity)

    fun plus(searchModule: SearchModule): SearchComponent
}

