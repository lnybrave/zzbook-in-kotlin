package com.lnybrave.zzbook.di.module

import com.lnybrave.zzbook.di.scope.PerActivity
import com.lnybrave.zzbook.mvp.contract.BookshelfContract
import dagger.Module
import dagger.Provides

@Module
class BookshelfModule(private val mView: BookshelfContract.View) {

    @Provides @PerActivity fun provideView() = mView
}