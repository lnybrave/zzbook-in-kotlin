package com.lnybrave.zzbook.mvp.contract

import com.lnybrave.zzbook.bean.Book
import com.lnybrave.zzbook.bean.APIList
import io.reactivex.Observable

/**
 * Created by lny on 2017/7/24.
 */
interface BookshelfContract {

    interface View {

        fun setData(results: List<Book>)
    }

    interface Model {

        fun getData(): Observable<List<Book>>
    }

    interface Presenter {

        fun getData()
    }
}