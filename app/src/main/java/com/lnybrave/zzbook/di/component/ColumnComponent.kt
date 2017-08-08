package com.lnybrave.zzbook.di.component

import com.lnybrave.zzbook.di.module.ColumnModule
import com.lnybrave.zzbook.di.scope.PerActivity
import com.lnybrave.zzbook.ui.fragment.ColumnFragment
import dagger.Subcomponent


@PerActivity
@Subcomponent(modules = arrayOf(ColumnModule::class))
interface ColumnComponent {
    fun inject(fragment: ColumnFragment)
}

