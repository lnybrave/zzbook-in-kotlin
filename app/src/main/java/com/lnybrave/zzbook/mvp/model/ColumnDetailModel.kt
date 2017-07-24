package com.lnybrave.zzbook.mvp.model

import com.lnybrave.zzbook.api.ZZBookApi
import com.lnybrave.zzbook.entity.ApiList
import com.lnybrave.zzbook.entity.Topic
import com.lnybrave.zzbook.mvp.contract.ColumnDetailContract
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by lny on 2017/7/24.
 */
class ColumnDetailModel
@Inject constructor(private val api: ZZBookApi) : ColumnDetailContract.Model {
    override fun getData(id: Int): Observable<ApiList<Topic>> {
        return api.getColumnDetail(id)
    }
}