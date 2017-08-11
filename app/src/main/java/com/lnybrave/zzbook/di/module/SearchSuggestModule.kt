package com.lnybrave.zzbook.di.module

import com.lnybrave.zzbook.di.scope.PerActivity
import com.lnybrave.zzbook.mvp.contract.SearchSuggestContract
import dagger.Module
import dagger.Provides

@Module
class SearchSuggestModule(private val mView: SearchSuggestContract.View) {

    @Provides @PerActivity fun provideView() = mView
}