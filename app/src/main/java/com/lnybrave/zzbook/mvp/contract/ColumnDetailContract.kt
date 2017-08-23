package com.lnybrave.zzbook.mvp.contract

import com.lnybrave.zzbook.bean.APIPage
import com.lnybrave.zzbook.bean.MixedBean
import io.reactivex.Observable

/**
 * Created by lny on 2017/7/24.
 */
interface ColumnDetailContract {

    interface View {

        fun setData(page: APIPage<MixedBean>)
    }

    interface Model {

        fun getData(id: Int, offset: Int = 0, limit: Int = 10): Observable<APIPage<MixedBean>>
    }

    interface Presenter {

        fun getData(id: Int, offset: Int= 0, limit: Int= 10)
    }
}