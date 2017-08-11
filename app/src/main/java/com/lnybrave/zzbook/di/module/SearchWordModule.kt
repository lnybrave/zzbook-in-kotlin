package com.lnybrave.zzbook.di.module

import com.lnybrave.zzbook.di.scope.PerActivity
import com.lnybrave.zzbook.mvp.contract.SearchWordContract
import dagger.Module
import dagger.Provides

@Module
class SearchWordModule(private val mView: SearchWordContract.View) {

    @Provides @PerActivity fun provideView() = mView
}