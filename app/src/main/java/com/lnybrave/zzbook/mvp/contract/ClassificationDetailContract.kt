package com.lnybrave.zzbook.mvp.contract

import com.lnybrave.zzbook.bean.APIPage
import com.lnybrave.zzbook.bean.Book
import io.reactivex.Observable

/**
 * Created by lny on 2017/7/24.
 */
interface ClassificationDetailContract {

    interface View {

        fun setData(page: APIPage<Book>)
    }

    interface Model {

        fun getData(firstId: Int, secondId: Int, offset: Int = 0, limit: Int = 10): Observable<APIPage<Book>>
    }

    interface Presenter {

        fun getData(firstId: Int, secondId: Int, offset: Int = 0, limit: Int = 10)
    }
}