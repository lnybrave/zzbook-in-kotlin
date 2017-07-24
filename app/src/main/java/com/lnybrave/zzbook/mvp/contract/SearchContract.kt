package com.lnybrave.zzbook.mvp.contract

import com.lnybrave.zzbook.entity.Book
import com.lnybrave.zzbook.entity.ApiList
import io.reactivex.Observable

/**
 * Created by lny on 2017/7/24.
 */
interface SearchContract {

    interface View {

        fun setData(results: List<Book>)
    }

    interface Model {

        fun getData(search: String, offset: Int): Observable<ApiList<Book>>
    }

    interface Presenter {

        fun getData(search: String, offset: Int)
    }
}