package com.lnybrave.zzbook.di.component

import com.lnybrave.zzbook.di.module.*
import com.lnybrave.zzbook.di.scope.PerActivity
import dagger.Component


@PerActivity
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(ActivityModule::class))
interface MainComponent : ActivityComponent {

    fun plus(bookshelfModule: BookshelfModule): BookshelfComponent

    fun plus(recommendationModule: RecommendationModule): RecommendationComponent

    fun plus(columnDetailModule: ColumnDetailModule): ColumnDetailComponent

    fun plus(rankingModule: RankingModule): RankingComponent

    fun plus(rankingDetailModule: RankingDetailModule): RankingDetailComponent

    fun plus(classificationModule: ClassificationModule): ClassificationComponent

    fun plus(classificationDetailModule: ClassificationDetailModule): ClassificationDetailComponent

//    fun plus(searchModule: SearchModule): SearchComponent

    fun plus(bookDetailModule: BookDetailModule): BookDetailComponent
}

