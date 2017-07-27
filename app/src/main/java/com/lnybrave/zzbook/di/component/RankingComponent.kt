package com.lnybrave.zzbook.di.component

import com.lnybrave.zzbook.di.scope.PerActivity
import com.lnybrave.zzbook.mvp.contract.RankingContract
import com.lnybrave.zzbook.ui.fragment.ClassificationFragment
import com.lnybrave.zzbook.ui.fragment.RankingFragment
import dagger.Module
import dagger.Provides
import dagger.Subcomponent


@PerActivity
@Subcomponent(modules = arrayOf(RankingModule::class))
interface RankingComponent {
    fun inject(fragment: RankingFragment)
}

@Module
class RankingModule(private val mView: RankingContract.View) {

    @Provides @PerActivity fun provideView() = mView
}