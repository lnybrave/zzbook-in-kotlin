package com.lnybrave.zzbook.mvp.contract

import com.lnybrave.zzbook.bean.Book
import io.reactivex.Observable

/**
 * Created by lny on 2017/7/24.
 */
interface ClassificationDetailContract {

    interface View {

        fun setData(results: List<Book>)
    }

    interface Model {

        fun getAll(firstId: Int, page: Int): Observable<List<Book>>

        fun getData(secondId: Int, page: Int): Observable<List<Book>>
    }

    interface Presenter {

        fun getAll(firstId: Int, page: Int)

        fun getData(secondId: Int, page: Int)
    }
}