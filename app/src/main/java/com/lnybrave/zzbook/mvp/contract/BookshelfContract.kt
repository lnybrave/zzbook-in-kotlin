package com.lnybrave.zzbook.mvp.contract

import com.lnybrave.zzbook.bean.Book
import com.lnybrave.zzbook.mvp.ErrorView
import com.lnybrave.zzbook.mvp.IPresenter
import com.lnybrave.zzbook.mvp.ProgressView
import io.reactivex.Observable

/**
 * Created by lny on 2017/7/24.
 */
interface BookshelfContract {

    interface View : ProgressView, ErrorView {

        fun setData(results: List<Book>)
    }

    interface Model {

        fun getData(): Observable<List<Book>>
    }

    interface Presenter : IPresenter {

        fun getData()
    }
}