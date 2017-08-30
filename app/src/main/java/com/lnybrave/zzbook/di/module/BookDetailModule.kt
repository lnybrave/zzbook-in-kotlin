package com.lnybrave.zzbook.di.module

import com.lnybrave.zzbook.di.scope.PerActivity
import com.lnybrave.zzbook.mvp.contract.BookDetailContract
import dagger.Module
import dagger.Provides

@Module
class BookDetailModule(private val mView: BookDetailContract.View) {

    @Provides @PerActivity fun provideView() = mView
}