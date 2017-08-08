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

    override fun getAll(firstId: Int, page: Int): Observable<List<Book>> {
        return api.getClassificationAll(firstId, page)
    }

    override fun getData(secondId: Int, page: Int): Observable<List<Book>> {
        return api.getClassificationDetail(secondId, page)
    }
}