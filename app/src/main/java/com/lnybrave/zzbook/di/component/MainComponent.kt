package com.lnybrave.zzbook.di.component

import com.lnybrave.zzbook.di.module.ActivityModule
import com.lnybrave.zzbook.di.scope.PerActivity
import com.lnybrave.zzbook.ui.activity.MainActivity
import com.lnybrave.zzbook.ui.activity.RankingActivity
import dagger.Component


@PerActivity
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(ActivityModule::class))
interface MainComponent : ActivityComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(rankingActivity: RankingActivity)

    fun plus(bookshelfModule: BookshelfModule): BookshelfComponent

    fun plus(recommendationModule: RecommendationModule): RecommendationComponent

    fun plus(classificationModule: ClassificationModule): ClassificationComponent

    fun plus(rankingModule: RankingModule): RankingComponent
}

