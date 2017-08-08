package com.lnybrave.zzbook.di.module

import com.lnybrave.zzbook.di.scope.PerActivity
import com.lnybrave.zzbook.mvp.contract.ClassificationContract
import dagger.Module
import dagger.Provides

@Module
class ClassificationModule(private val mView: ClassificationContract.View) {

    @Provides @PerActivity fun provideView() = mView
}