package com.lnybrave.zzbook.di.component

import com.lnybrave.zzbook.di.module.RankingDetailModule
import com.lnybrave.zzbook.di.scope.PerActivity
import com.lnybrave.zzbook.ui.fragment.RankingFragment
import dagger.Subcomponent


@PerActivity
@Subcomponent(modules = arrayOf(RankingDetailModule::class))
interface RankingDetailComponent {
    fun inject(fragment: RankingFragment)
}

