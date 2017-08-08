package com.lnybrave.zzbook.di.component

import com.lnybrave.zzbook.di.module.ClassificationDetailModule
import com.lnybrave.zzbook.di.scope.PerActivity
import com.lnybrave.zzbook.ui.fragment.ClassificationDetailFragment
import dagger.Subcomponent


@PerActivity
@Subcomponent(modules = arrayOf(ClassificationDetailModule::class))
interface ClassificationDetailComponent {
    fun inject(fragment: ClassificationDetailFragment)
}

