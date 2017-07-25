package com.lnybrave.zzbook.mvp.contract

import com.lnybrave.zzbook.bean.APIList
import com.lnybrave.zzbook.bean.Topic
import io.reactivex.Observable

/**
 * Created by lny on 2017/7/24.
 */
interface RecommendationContract {

    interface View {

        fun setData(results: List<Topic>)
    }

    interface Model {

        fun getData(): Observable<APIList<Topic>>
    }

    interface Presenter {

        fun getData()
    }
}