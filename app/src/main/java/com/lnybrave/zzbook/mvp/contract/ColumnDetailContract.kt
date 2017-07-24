package com.lnybrave.zzbook.mvp.contract

import com.lnybrave.zzbook.entity.ApiList
import com.lnybrave.zzbook.entity.Topic
import io.reactivex.Observable

/**
 * Created by lny on 2017/7/24.
 */
interface ColumnDetailContract {

    interface View {

        fun setData(results: List<Topic>)
    }

    interface Model {

        fun getData(id: Int): Observable<ApiList<Topic>>
    }

    interface Presenter {

        fun getData(id: Int)
    }
}