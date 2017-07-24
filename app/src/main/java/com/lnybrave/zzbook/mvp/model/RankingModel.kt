package com.lnybrave.zzbook.mvp.model

import com.lnybrave.zzbook.api.ZZBookApi
import com.lnybrave.zzbook.entity.ApiList
import com.lnybrave.zzbook.entity.Ranking
import com.lnybrave.zzbook.mvp.contract.RankingContract
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by lny on 2017/7/24.
 */
class RankingModel
@Inject constructor(private val api: ZZBookApi) : RankingContract.Model {
    override fun getData(): Observable<ApiList<Ranking>> {
        return api.getRankingList()
    }
}