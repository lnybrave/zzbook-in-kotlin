package com.lnybrave.zzbook.di.module

import com.lnybrave.zzbook.di.scope.PerActivity
import com.lnybrave.zzbook.mvp.contract.RecommendationContract
import dagger.Module
import dagger.Provides

@Module
class RecommendationModule(private val mView: RecommendationContract.View) {

    @Provides @PerActivity fun provideView() = mView
}