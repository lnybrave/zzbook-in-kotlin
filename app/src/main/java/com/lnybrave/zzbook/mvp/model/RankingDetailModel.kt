package com.lnybrave.zzbook.mvp.model

import com.lnybrave.zzbook.api.ZZBookApi
import com.lnybrave.zzbook.bean.APIPage
import com.lnybrave.zzbook.bean.Book
import com.lnybrave.zzbook.mvp.contract.RankingDetailContract
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by lny on 2017/7/24.
 */
class RankingDetailModel
@Inject constructor(private val api: ZZBookApi) : RankingDetailContract.Model {
    override fun getData(id: Int, page: Int): Observable<APIPage<Book>> {
        return api.getRankingDetail(id, page)
    }
}