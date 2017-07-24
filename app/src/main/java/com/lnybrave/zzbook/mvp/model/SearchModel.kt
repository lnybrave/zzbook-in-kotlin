package com.lnybrave.zzbook.mvp.model

import com.lnybrave.zzbook.api.ZZBookApi
import com.lnybrave.zzbook.entity.Book
import com.lnybrave.zzbook.entity.ApiList
import com.lnybrave.zzbook.mvp.contract.SearchContract
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by lny on 2017/7/24.
 */
class SearchModel
@Inject constructor(private val api: ZZBookApi) : SearchContract.Model {
    override fun getData(search: String, offset: Int): Observable<ApiList<Book>> {
        return api.getSearch(search, offset)
    }
}