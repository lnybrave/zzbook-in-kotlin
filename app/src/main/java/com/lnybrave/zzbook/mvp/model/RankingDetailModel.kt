package com.lnybrave.zzbook.mvp.model

import com.lnybrave.zzbook.api.ZZBookApi
import com.lnybrave.zzbook.entity.ApiPage
import com.lnybrave.zzbook.entity.Book
import com.lnybrave.zzbook.mvp.contract.RankingDetailContract
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by lny on 2017/7/24.
 */
class RankingDetailModel
@Inject constructor(private val api: ZZBookApi) : RankingDetailContract.Model {
    override fun getData(id: Int, page: Int): Observable<ApiPage<Book>> {
        return api.getRankingDetail(id, page)
    }
}