package com.lnybrave.zzbook.di.component

import com.lnybrave.zzbook.di.module.RankingModule
import com.lnybrave.zzbook.di.scope.PerActivity
import com.lnybrave.zzbook.ui.activity.RankingActivity
import dagger.Subcomponent


@PerActivity
@Subcomponent(modules = arrayOf(RankingModule::class))
interface RankingComponent {

    fun inject(rankingActivity: RankingActivity)
}

