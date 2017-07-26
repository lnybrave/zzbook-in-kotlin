package com.lnybrave.zzbook.di.component

import com.lnybrave.zzbook.di.scope.PerActivity
import com.lnybrave.zzbook.ui.fragment.BookshelfFragment
import dagger.Subcomponent

@PerActivity
@Subcomponent
interface MainFragmentComponent {

    fun inject(fragment: BookshelfFragment): Unit
}