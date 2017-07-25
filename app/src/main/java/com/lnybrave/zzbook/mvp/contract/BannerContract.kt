package com.lnybrave.zzbook.mvp.contract

import com.lnybrave.zzbook.bean.APIList
import com.lnybrave.zzbook.bean.Banner
import io.reactivex.Observable

/**
 * Created by lny on 2017/7/24.
 */
interface BannerContract {

    interface View {

        fun setData(results: List<Banner>)
    }

    interface Model {

        fun getData(): Observable<APIList<Banner>>
    }

    interface Presenter {

        fun getData()
    }
}