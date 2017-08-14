package com.lnybrave.zzbook.mvp.model

import com.lnybrave.zzbook.api.ZZBookApi
import com.lnybrave.zzbook.bean.Search
import com.lnybrave.zzbook.mvp.contract.SearchHotContract
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

/**
 * Created by lny on 2017/7/24.
 */
class SearchHotModel
@Inject constructor(private val api: ZZBookApi) : SearchHotContract.Model {
    override fun getData(): Observable<Search> {
        return api.getSearchWords().zipWith(api.getSearchTop(5), BiFunction { t1, t2 -> Search(t1,t2) })
    }
}