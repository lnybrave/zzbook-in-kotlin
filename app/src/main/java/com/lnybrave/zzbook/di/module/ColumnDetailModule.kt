package com.lnybrave.zzbook.di.module

import com.lnybrave.zzbook.di.scope.PerActivity
import com.lnybrave.zzbook.mvp.contract.ColumnDetailContract
import dagger.Module
import dagger.Provides

@Module
class ColumnDetailModule(private val mView: ColumnDetailContract.View) {
    @Provides @PerActivity fun provideView() = mView
}