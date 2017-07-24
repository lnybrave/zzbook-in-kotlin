package com.lnybrave.zzbook.mvp.contract

import com.lnybrave.zzbook.entity.Classification
import com.lnybrave.zzbook.entity.ApiList
import io.reactivex.Observable

/**
 * Created by lny on 2017/7/24.
 */
interface ClassificationContract {

    interface View {

        fun setData(results: List<Classification>)
    }

    interface Model {

        fun getData(): Observable<ApiList<Classification>>
    }

    interface Presenter {

        fun getData()
    }
}