package com.lnybrave.zzbook.mvp.contract

import com.lnybrave.zzbook.bean.APIPage
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

        fun getData(id: Int, page: Int): Observable<APIPage<Book>>
    }

    interface Presenter {

        fun getData(id: Int, page: Int)
    }
}