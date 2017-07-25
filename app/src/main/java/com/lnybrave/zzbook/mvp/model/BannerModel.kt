package com.lnybrave.zzbook.mvp.model

import com.lnybrave.zzbook.api.ZZBookApi
import com.lnybrave.zzbook.bean.APIList
import com.lnybrave.zzbook.bean.Banner
import com.lnybrave.zzbook.mvp.contract.BannerContract
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by lny on 2017/7/24.
 */
class BannerModel
@Inject constructor(private val api: ZZBookApi) : BannerContract.Model {
    override fun getData(): Observable<APIList<Banner>> {
        return api.getBannerList()
    }
}