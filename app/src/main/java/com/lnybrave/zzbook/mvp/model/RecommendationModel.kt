package com.lnybrave.zzbook.mvp.model

import com.lnybrave.zzbook.api.ZZBookApi
import com.lnybrave.zzbook.bean.APIList
import com.lnybrave.zzbook.bean.Topic
import com.lnybrave.zzbook.mvp.contract.RecommendationContract
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by lny on 2017/7/24.
 */
class RecommendationModel
@Inject constructor(private val api: ZZBookApi) : RecommendationContract.Model {
    override fun getData(): Observable<APIList<Topic>> {
        return api.getRecommendation()
    }
}