package com.lnybrave.zzbook.mvp.model

import com.lnybrave.zzbook.api.ZZBookApi
import com.lnybrave.zzbook.bean.Recommendation
import com.lnybrave.zzbook.mvp.contract.RecommendationContract
import io.reactivex.Observable
import io.reactivex.functions.Function3
import javax.inject.Inject

/**
 * Created by lny on 2017/7/24.
 */
class RecommendationModel
@Inject constructor(private val api: ZZBookApi) : RecommendationContract.Model {
    override fun getData(): Observable<Recommendation> {
        return Observable.zip(api.getBannerList(),
                api.getSubjectRecommendation(),
                api.getRecommendation(),
                Function3 { t1, t2, t3 -> Recommendation(banners = t1, subjects = t2, topics = t3, books = null) })
    }
}