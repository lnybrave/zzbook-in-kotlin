package com.lnybrave.zzbook.mvp.contract

import com.lnybrave.zzbook.bean.Search
import io.reactivex.Observable

/**
 * Created by lny on 2017/7/24.
 */
interface SearchHotContract {

    interface View {

        fun setData(results: Search)
    }

    interface Model {

        fun getData(): Observable<Search>
    }

    interface Presenter {

        fun getData()
    }
}