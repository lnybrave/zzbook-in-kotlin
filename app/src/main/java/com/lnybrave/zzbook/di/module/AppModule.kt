package com.lnybrave.zzbook.di.module

import android.content.Context
import dagger.Module
import dagger.Provides


@Module
class AppModule(private val context: Context) {

    @Provides fun provideContext() = context
}
