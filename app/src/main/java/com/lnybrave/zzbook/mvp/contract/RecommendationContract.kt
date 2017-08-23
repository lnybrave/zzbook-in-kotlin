package com.lnybrave.zzbook.mvp.contract

import com.lnybrave.zzbook.bean.APIPage
import com.lnybrave.zzbook.bean.MixedBean
import com.lnybrave.zzbook.bean.RecommendationZip
import io.reactivex.Observable

/**
 * Created by lny on 2017/7/24.
 */
interface RecommendationContract {

    interface View {

        fun setData(data: RecommendationZip)

        fun setData(page: APIPage<MixedBean>)
    }

    interface Model {

        fun getData(): Observable<RecommendationZip>

        fun getData(offset: Int, limit: Int): Observable<APIPage<MixedBean>>
    }

    interface Presenter {

        fun getData()

        fun getData(offset: Int, limit: Int)
    }
}