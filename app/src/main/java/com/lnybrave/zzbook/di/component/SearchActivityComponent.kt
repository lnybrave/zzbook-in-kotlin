package com.lnybrave.zzbook.di.component

import com.lnybrave.zzbook.di.module.*
import com.lnybrave.zzbook.di.scope.PerActivity
import com.lnybrave.zzbook.ui.activity.SearchActivity
import dagger.Component


@PerActivity
@Component(
        dependencies = arrayOf(
                AppComponent::class
        ),
        modules = arrayOf(
                ActivityModule::class,
                SearchHotModule::class,
                SearchSuggestModule::class,
                SearchActivityModule::class
        )
)
interface SearchActivityComponent : ActivityComponent {
    fun inject(activity: SearchActivity)

    fun plus(searchModule: SearchModule): SearchComponent
}

