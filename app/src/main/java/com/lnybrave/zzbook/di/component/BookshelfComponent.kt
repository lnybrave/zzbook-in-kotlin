package com.lnybrave.zzbook.di.component

import com.lnybrave.zzbook.di.scope.PerActivity
import com.lnybrave.zzbook.mvp.contract.BookshelfContract
import com.lnybrave.zzbook.ui.fragment.BookshelfFragment
import dagger.Module
import dagger.Provides
import dagger.Subcomponent


@PerActivity
@Subcomponent(modules = arrayOf(BookshelfModule::class))
interface BookshelfComponent {
    fun inject(fragment: BookshelfFragment)
}

@Module
class BookshelfModule(private val mView: BookshelfContract.View) {

    @Provides @PerActivity fun provideView() = mView
}