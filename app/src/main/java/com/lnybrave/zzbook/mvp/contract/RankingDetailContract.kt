package com.lnybrave.zzbook.mvp.contract

import com.lnybrave.zzbook.entity.ApiPage
import com.lnybrave.zzbook.entity.Book
import io.reactivex.Observable

/**
 * Created by lny on 2017/7/24.
 */
interface RankingDetailContract {

    interface View {

        fun setData(results: List<Book>)
    }

    interface Model {

        fun getData(id: Int, page: Int): Observable<ApiPage<Book>>
    }

    interface Presenter {

        fun getData(id: Int, page: Int)
    }
}