package com.lnybrave.zzbook.mvp.model

import com.lnybrave.zzbook.api.ZZBookApi
import com.lnybrave.zzbook.bean.APIPage
import com.lnybrave.zzbook.bean.Column
import com.lnybrave.zzbook.bean.MixedBean
import com.lnybrave.zzbook.bean.Topic
import com.lnybrave.zzbook.mvp.contract.ColumnDetailContract
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by lny on 2017/7/24.
 */
class ColumnDetailModel
@Inject constructor(private val api: ZZBookApi) : ColumnDetailContract.Model {
    override fun getData(id: Int, offset: Int, limit: Int): Observable<APIPage<MixedBean>> {
        return api.getColumnDetail(id, offset, limit)
    }
}