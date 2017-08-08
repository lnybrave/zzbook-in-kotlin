package com.lnybrave.zzbook.di.component

import com.lnybrave.zzbook.di.module.RecommendationModule
import com.lnybrave.zzbook.di.scope.PerActivity
import com.lnybrave.zzbook.ui.fragment.RecommendationFragment
import dagger.Subcomponent


@PerActivity
@Subcomponent(modules = arrayOf(RecommendationModule::class))
interface RecommendationComponent {
    fun inject(fragment: RecommendationFragment)
}

