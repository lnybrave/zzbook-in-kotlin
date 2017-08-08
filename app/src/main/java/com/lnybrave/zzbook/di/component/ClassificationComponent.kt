package com.lnybrave.zzbook.di.component

import com.lnybrave.zzbook.di.module.ClassificationModule
import com.lnybrave.zzbook.di.scope.PerActivity
import com.lnybrave.zzbook.ui.activity.ClassificationDetailActivity
import com.lnybrave.zzbook.ui.fragment.ClassificationFragment
import dagger.Subcomponent


@PerActivity
@Subcomponent(modules = arrayOf(ClassificationModule::class))
interface ClassificationComponent {
    fun inject(fragment: ClassificationFragment)
}

