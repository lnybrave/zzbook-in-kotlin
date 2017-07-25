package com.lnybrave.zzbook.mvp.model

import com.lnybrave.zzbook.api.ZZBookApi
import com.lnybrave.zzbook.bean.*
import com.lnybrave.zzbook.mvp.contract.ClassificationDetailContract
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by lny on 2017/7/24.
 */
class ClassificationDetailModel
@Inject constructor(private val api: ZZBookApi) : ClassificationDetailContract.Model {
    override fun getData(id: Int, page: Int): Observable<APIPage<Book>> {
        return api.getClassificationDetail(id, page)
    }
}