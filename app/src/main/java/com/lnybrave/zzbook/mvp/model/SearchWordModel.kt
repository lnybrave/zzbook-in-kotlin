package com.lnybrave.zzbook.mvp.model

import com.lnybrave.zzbook.api.ZZBookApi
import com.lnybrave.zzbook.entity.ApiList
import com.lnybrave.zzbook.mvp.contract.SearchWordContract
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by lny on 2017/7/24.
 */
class SearchWordModel
@Inject constructor(private val api: ZZBookApi) : SearchWordContract.Model {
    override fun getData(): Observable<ApiList<String>> {
        return api.getSearch()
    }
}