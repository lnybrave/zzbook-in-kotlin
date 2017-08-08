package com.lnybrave.zzbook.di.component

import com.lnybrave.zzbook.di.module.*
import com.lnybrave.zzbook.di.scope.PerActivity
import com.lnybrave.zzbook.ui.activity.MainActivity
import dagger.Component


@PerActivity
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(ActivityModule::class))
interface MainComponent : ActivityComponent {

    fun inject(mainActivity: MainActivity)

    fun plus(bookshelfModule: BookshelfModule): BookshelfComponent

    fun plus(recommendationModule: RecommendationModule): RecommendationComponent

    fun plus(columnModule: ColumnModule): ColumnComponent

    fun plus(rankingModule: RankingModule): RankingComponent

    fun plus(rankingDetailModule: RankingDetailModule): RankingDetailComponent

    fun plus(classificationModule: ClassificationModule): ClassificationComponent

    fun plus(classificationDetailModule: ClassificationDetailModule): ClassificationDetailComponent
}

