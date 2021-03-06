package com.lnybrave.zzbook.mvp.contract

import com.lnybrave.zzbook.bean.APIPage
import com.lnybrave.zzbook.bean.Book
import com.lnybrave.zzbook.mvp.EmptyView
import com.lnybrave.zzbook.mvp.ErrorView
import com.lnybrave.zzbook.mvp.IPresenter
import com.lnybrave.zzbook.mvp.LoadView
import io.reactivex.Observable

/**
 * Created by lny on 2017/7/24.
 */
interface SearchContract {

    interface View : EmptyView, ErrorView, LoadView {

        fun setData(data: APIPage<Book>)
    }

    interface Model {

        fun getData(search: String, offset: Int, limit: Int): Observable<APIPage<Book>>
    }

    interface Presenter : IPresenter {

        fun getData(search: String, offset: Int = 0, limit: Int = 10)
    }
}