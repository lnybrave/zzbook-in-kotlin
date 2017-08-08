package com.lnybrave.zzbook.di.module

import com.lnybrave.zzbook.di.scope.PerActivity
import com.lnybrave.zzbook.mvp.contract.RankingDetailContract
import dagger.Module
import dagger.Provides

@Module
class RankingDetailModule(private val mView: RankingDetailContract.View) {
    @Provides @PerActivity fun provideView() = mView
}