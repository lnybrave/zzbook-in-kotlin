package com.lnybrave.zzbook.mvp.contract

import com.lnybrave.zzbook.bean.APIPage
import com.lnybrave.zzbook.bean.MixedBean
import com.lnybrave.zzbook.mvp.EmptyView
import com.lnybrave.zzbook.mvp.ErrorView
import com.lnybrave.zzbook.mvp.IPresenter
import com.lnybrave.zzbook.mvp.LoadView
import io.reactivex.Observable

/**
 * Created by lny on 2017/7/24.
 */
interface ColumnDetailContract {

    interface View : EmptyView, ErrorView, LoadView {

        fun setData(page: APIPage<MixedBean>)
    }

    interface Model {

        fun getData(id: Int, offset: Int, limit: Int): Observable<APIPage<MixedBean>>
    }

    interface Presenter : IPresenter {

        fun getData(id: Int, offset: Int = 0, limit: Int = 10)
    }
}