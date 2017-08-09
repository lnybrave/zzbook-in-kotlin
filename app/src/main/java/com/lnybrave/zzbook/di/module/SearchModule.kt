package com.lnybrave.zzbook.di.module

import com.lnybrave.zzbook.di.scope.PerActivity
import com.lnybrave.zzbook.mvp.contract.SearchContract
import dagger.Module
import dagger.Provides

@Module
class SearchModule(private val mView: SearchContract.View) {

    @Provides @PerActivity fun provideView() = mView
}