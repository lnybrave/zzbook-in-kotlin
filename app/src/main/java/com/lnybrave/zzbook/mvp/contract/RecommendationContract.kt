package com.lnybrave.zzbook.mvp.contract

import com.lnybrave.zzbook.bean.*
import com.lnybrave.zzbook.mvp.EmptyView
import com.lnybrave.zzbook.mvp.ErrorView
import com.lnybrave.zzbook.mvp.IPresenter
import com.lnybrave.zzbook.mvp.ProgressView
import io.reactivex.Observable

/**
 * Created by lny on 2017/7/24.
 */
interface RecommendationContract {

    interface View : EmptyView, ErrorView, ProgressView {

        fun setBannerList(data: List<Banner>)

        fun setMenuList(data: List<StackMenu>)

        fun setData(page: APIPage<MixedBean>)
    }

    interface Model {

        fun getData(): Observable<RecommendationZip>

        fun getData(offset: Int, limit: Int): Observable<APIPage<MixedBean>>
    }

    interface Presenter : IPresenter {

        fun getData()

        fun getData(offset: Int, limit: Int)
    }
}