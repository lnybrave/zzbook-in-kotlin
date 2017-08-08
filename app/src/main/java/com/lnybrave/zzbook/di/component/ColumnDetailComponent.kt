package com.lnybrave.zzbook.di.component

import com.lnybrave.zzbook.di.module.ColumnDetailModule
import com.lnybrave.zzbook.di.scope.PerActivity
import com.lnybrave.zzbook.ui.fragment.ColumnDetailFragment
import dagger.Subcomponent


@PerActivity
@Subcomponent(modules = arrayOf(ColumnDetailModule::class))
interface ColumnDetailComponent {
    fun inject(fragment: ColumnDetailFragment)
}

