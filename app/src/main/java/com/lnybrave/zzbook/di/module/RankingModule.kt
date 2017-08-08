package com.lnybrave.zzbook.di.module

import com.lnybrave.zzbook.di.scope.PerActivity
import com.lnybrave.zzbook.mvp.contract.RankingContract
import dagger.Module
import dagger.Provides

@Module
class RankingModule(private val mView: RankingContract.View) {

    @Provides @PerActivity fun provideView() = mView
}