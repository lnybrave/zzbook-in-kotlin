package com.lnybrave.zzbook.mvp.model

import com.lnybrave.zzbook.api.ZZBookApi
import com.lnybrave.zzbook.bean.APIPage
import com.lnybrave.zzbook.bean.MixedBean
import com.lnybrave.zzbook.bean.RecommendationZip
import com.lnybrave.zzbook.mvp.contract.RecommendationContract
import io.reactivex.Observable
import io.reactivex.functions.Function3
import javax.inject.Inject

/**
 * Created by lny on 2017/7/24.
 */
class RecommendationModel
@Inject constructor(private val api: ZZBookApi) : RecommendationContract.Model {
    override fun getData(limit: Int): Observable<RecommendationZip> {
        return Observable.zip(api.getBannerList(),
                api.getStackMenu(),
                api.getRecommendation(0, 10),
                Function3 { t1, t2, t3 -> RecommendationZip(banners = t1, menus = t2, page = t3) })
    }

    override fun getData(offset: Int, limit: Int): Observable<APIPage<MixedBean>> {
        return api.getRecommendation(offset, limit)
    }
}