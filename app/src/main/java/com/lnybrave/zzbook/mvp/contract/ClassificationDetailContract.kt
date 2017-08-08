package com.lnybrave.zzbook.mvp.contract

import com.lnybrave.zzbook.bean.Classification
import io.reactivex.Observable

/**
 * Created by lny on 2017/7/24.
 */
interface ClassificationDetailContract {

    interface View {

        fun setData(results: Classification)
    }

    interface Model {

        fun getData(firstId: Int, secondId: Int, page: Int): Observable<Classification>
    }

    interface Presenter {

        fun getData(firstId: Int, secondId: Int, page: Int)
    }
}