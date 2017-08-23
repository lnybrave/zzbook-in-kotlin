package com.lnybrave.zzbook.mvp.model

import com.lnybrave.zzbook.api.ZZBookApi
import com.lnybrave.zzbook.bean.APIList
import com.lnybrave.zzbook.bean.Ranking
import com.lnybrave.zzbook.mvp.contract.RankingContract
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by lny on 2017/7/24.
 */
class RankingModel
@Inject constructor(private val api: ZZBookApi) : RankingContract.Model {
    override fun getData(): Observable<List<Ranking>> {
        return api.getRanking(null)
    }
}