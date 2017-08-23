package com.lnybrave.zzbook.mvp.contract

import com.lnybrave.zzbook.bean.Book
import io.reactivex.Observable

/**
 * Created by lny on 2017/7/24.
 */
interface BookDetailContract {

    interface View {

        fun setData(results: Book)
    }

    interface Model {

        fun getData(id: Int): Observable<Book>
    }

    interface Presenter {

        fun getData(id: Int)
    }
}