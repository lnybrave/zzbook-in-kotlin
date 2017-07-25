package com.lnybrave.zzbook.mvp.contract

import com.lnybrave.zzbook.bean.APIList
import io.reactivex.Observable

/**
 * Created by lny on 2017/7/24.
 */
interface SearchWordContract {

    interface View {

        fun setData(results: List<String>)
    }

    interface Model {

        fun getData(): Observable<APIList<String>>
    }

    interface Presenter {

        fun getData()
    }
}