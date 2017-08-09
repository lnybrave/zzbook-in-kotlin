package com.lnybrave.zzbook.mvp.contract

import com.lnybrave.zzbook.bean.APIPage
import com.lnybrave.zzbook.bean.Book
import io.reactivex.Observable

/**
 * Created by lny on 2017/7/24.
 */
interface SearchContract {

    interface View {

        fun setData(results: APIPage<Book>)
    }

    interface Model {

        fun getData(search: String, limit: Int, offset: Int): Observable<APIPage<Book>>
    }

    interface Presenter {

        fun getData(search: String, limit: Int, offset: Int)
    }
}