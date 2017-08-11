package com.lnybrave.zzbook.mvp.model

import com.lnybrave.zzbook.api.ZZBookApi
import com.lnybrave.zzbook.bean.SearchWord
import com.lnybrave.zzbook.mvp.contract.SearchSuggestContract
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by lny on 2017/7/24.
 */
class SearchSuggestModel
@Inject constructor(private val api: ZZBookApi) : SearchSuggestContract.Model {
    override fun getData(content: String): Observable<List<SearchWord>> {
        return api.getSearchSuggest(content)
    }
}