package com.lnybrave.zzbook.di.component

import com.lnybrave.zzbook.di.scope.PerActivity
import com.lnybrave.zzbook.mvp.contract.RecommendationContract
import com.lnybrave.zzbook.ui.fragment.BookshelfFragment
import com.lnybrave.zzbook.ui.fragment.RecommendationFragment
import dagger.Module
import dagger.Provides
import dagger.Subcomponent


@PerActivity
@Subcomponent(modules = arrayOf(RecommendationModule::class))
interface RecommendationComponent {
    fun inject(fragment: RecommendationFragment)
}

@Module
class RecommendationModule(private val mView: RecommendationContract.View) {

    @Provides @PerActivity fun provideView() = mView
}