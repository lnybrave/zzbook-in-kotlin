package com.lnybrave.zzbook.di.component

import com.lnybrave.zzbook.di.module.BookshelfModule
import com.lnybrave.zzbook.di.scope.PerActivity
import com.lnybrave.zzbook.ui.fragment.BookshelfFragment
import dagger.Subcomponent


@PerActivity
@Subcomponent(modules = arrayOf(BookshelfModule::class))
interface BookshelfComponent {
    fun inject(fragment: BookshelfFragment)
}