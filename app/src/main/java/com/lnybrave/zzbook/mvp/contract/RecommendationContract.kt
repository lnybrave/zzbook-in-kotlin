package com.lnybrave.zzbook.mvp.contract

import com.lnybrave.zzbook.bean.Recommendation
import io.reactivex.Observable

/**
 * Created by lny on 2017/7/24.
 */
interface RecommendationContract {

    interface View {

        fun setData(results: Recommendation)
    }

    interface Model {

        fun getData(): Observable<Recommendation>
    }

    interface Presenter {

        fun getData()
    }
}