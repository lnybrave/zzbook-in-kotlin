package com.lnybrave.zzbook.mvp.contract

import com.lnybrave.zzbook.bean.Book
import com.lnybrave.zzbook.mvp.ErrorView
import com.lnybrave.zzbook.mvp.IPresenter
import io.reactivex.Observable

/**
 * Created by lny on 2017/7/24.
 */
interface BookDetailContract {

    interface View : ErrorView {

        fun setData(results: Book)
    }

    interface Model {

        fun getData(id: Int): Observable<Book>
    }

    interface Presenter : IPresenter {

        fun getData(id: Int)
    }
}