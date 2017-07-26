package com.lnybrave.zzbook.di.module

import com.lnybrave.zzbook.mvp.contract.BookshelfContract
import dagger.Module
import dagger.Provides

@Module
class MainModule(private val mView: BookshelfContract.View) {
    @Provides fun provideView() = mView
}