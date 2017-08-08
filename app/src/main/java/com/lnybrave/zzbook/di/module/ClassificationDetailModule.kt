package com.lnybrave.zzbook.di.module

import com.lnybrave.zzbook.di.scope.PerActivity
import com.lnybrave.zzbook.mvp.contract.ClassificationDetailContract
import dagger.Module
import dagger.Provides

@Module
class ClassificationDetailModule(private val mView: ClassificationDetailContract.View) {
    @Provides @PerActivity fun provideView() = mView
}