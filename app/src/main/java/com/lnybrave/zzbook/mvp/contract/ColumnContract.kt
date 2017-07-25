package com.lnybrave.zzbook.mvp.contract

import com.lnybrave.zzbook.bean.Column
import com.lnybrave.zzbook.bean.APIList
import io.reactivex.Observable

/**
 * Created by lny on 2017/7/24.
 */
interface ColumnContract {

    interface View {

        fun setData(results: List<Column>)
    }

    interface Model {

        fun getData(): Observable<APIList<Column>>
    }

    interface Presenter {

        fun getData()
    }
}