package com.lnybrave.zzbook.mvp.contract

import com.lnybrave.zzbook.bean.SearchWord
import io.reactivex.Observable

/**
 * Created by lny on 2017/7/24.
 */
interface SearchSuggestContract {

    interface View {

        fun setData(data: List<SearchWord>)
    }

    interface Model {

        fun getData(content: String): Observable<List<SearchWord>>
    }

    interface Presenter {

        fun getData(content: String)
    }
}