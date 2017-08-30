package com.lnybrave.zzbook.mvp.contract

import com.lnybrave.zzbook.bean.Classification
import com.lnybrave.zzbook.bean.APIList
import com.lnybrave.zzbook.mvp.EmptyView
import com.lnybrave.zzbook.mvp.ErrorView
import com.lnybrave.zzbook.mvp.IPresenter
import com.lnybrave.zzbook.mvp.ProgressView
import io.reactivex.Observable

/**
 * Created by lny on 2017/7/24.
 */
interface ClassificationContract {

    interface View : EmptyView, ErrorView, ProgressView {

        fun setData(results: List<Classification>)
    }

    interface Model {

        fun getData(): Observable<List<Classification>>
    }

    interface Presenter : IPresenter {

        fun getData()
    }
}