package com.lnybrave.zzbook.di.module

import android.app.Activity
import com.lnybrave.zzbook.di.scope.PerActivity
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: Activity) {

    @Provides @PerActivity fun provideActivity() = activity
}