package com.lnybrave.zzbook.di.module

import com.lnybrave.zzbook.di.scope.PerActivity
import com.lnybrave.zzbook.mvp.contract.SearchHotContract
import dagger.Module
import dagger.Provides

@Module
class SearchHotModule(private val mView: SearchHotContract.View) {

    @Provides @PerActivity fun provideView() = mView
}