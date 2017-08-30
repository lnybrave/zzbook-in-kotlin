package com.lnybrave.zzbook.di.component

import com.lnybrave.zzbook.di.module.BookDetailModule
import com.lnybrave.zzbook.di.scope.PerActivity
import com.lnybrave.zzbook.ui.activity.BookActivity
import dagger.Subcomponent


@PerActivity
@Subcomponent(modules = arrayOf(BookDetailModule::class))
interface BookDetailComponent {

    fun inject(bookDetailActivity: BookActivity)
}

