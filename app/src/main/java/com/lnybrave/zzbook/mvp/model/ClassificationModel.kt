package com.lnybrave.zzbook.mvp.model

import com.lnybrave.zzbook.api.ZZBookApi
import com.lnybrave.zzbook.entity.Classification
import com.lnybrave.zzbook.entity.ApiList
import com.lnybrave.zzbook.mvp.contract.ClassificationContract
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by lny on 2017/7/24.
 */
class ClassificationModel
@Inject constructor(private val api: ZZBookApi) : ClassificationContract.Model {
    override fun getData(): Observable<ApiList<Classification>> {
        return api.getClassificationList()
    }
}