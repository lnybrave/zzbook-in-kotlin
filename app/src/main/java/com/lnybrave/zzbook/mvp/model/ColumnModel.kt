package com.lnybrave.zzbook.mvp.model

import com.lnybrave.zzbook.api.ZZBookApi
import com.lnybrave.zzbook.entity.Column
import com.lnybrave.zzbook.entity.ApiList
import com.lnybrave.zzbook.mvp.contract.ColumnContract
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by lny on 2017/7/24.
 */
class ColumnModel
@Inject constructor(private val api: ZZBookApi) : ColumnContract.Model {
    override fun getData(): Observable<ApiList<Column>> {
        return api.getColumnList()
    }
}