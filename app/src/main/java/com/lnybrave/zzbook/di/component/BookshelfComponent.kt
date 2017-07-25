package com.wingsofts.gankclient.di.component

import com.lnybrave.zzbook.mvp.contract.BookshelfContract
import com.lnybrave.zzbook.ui.fragment.BookshelfFragment
import dagger.Module
import dagger.Provides
import dagger.Subcomponent


@Subcomponent(modules = arrayOf(BookshelfModule::class))
interface BookshelfComponent {
    fun inject(fragment: BookshelfFragment)
}

@Module
class BookshelfModule(private val mView: BookshelfContract.View) {
    @Provides fun getView() = mView
}