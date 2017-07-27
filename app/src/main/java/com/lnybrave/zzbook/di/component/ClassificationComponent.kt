package com.lnybrave.zzbook.di.component

import com.lnybrave.zzbook.di.scope.PerActivity
import com.lnybrave.zzbook.mvp.contract.ClassificationContract
import com.lnybrave.zzbook.ui.fragment.ClassificationFragment
import dagger.Module
import dagger.Provides
import dagger.Subcomponent


@PerActivity
@Subcomponent(modules = arrayOf(ClassificationModule::class))
interface ClassificationComponent {
    fun inject(fragment: ClassificationFragment)
}

@Module
class ClassificationModule(private val mView: ClassificationContract.View) {

    @Provides @PerActivity fun provideView() = mView
}