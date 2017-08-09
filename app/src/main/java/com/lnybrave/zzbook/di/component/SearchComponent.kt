package com.lnybrave.zzbook.di.component

import com.lnybrave.zzbook.di.module.SearchModule
import com.lnybrave.zzbook.di.scope.PerActivity
import com.lnybrave.zzbook.ui.fragment.RecommendationFragment
import com.lnybrave.zzbook.ui.fragment.SearchFragment
import dagger.Subcomponent


@PerActivity
@Subcomponent(modules = arrayOf(SearchModule::class))
interface SearchComponent {
    fun inject(fragment: SearchFragment)
}

