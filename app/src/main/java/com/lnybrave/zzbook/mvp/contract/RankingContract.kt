package com.lnybrave.zzbook.mvp.contract

import com.lnybrave.zzbook.bean.Ranking
import com.lnybrave.zzbook.mvp.EmptyView
import com.lnybrave.zzbook.mvp.ErrorView
import com.lnybrave.zzbook.mvp.IPresenter
import com.lnybrave.zzbook.mvp.ProgressView
import io.reactivex.Observable

/**
 * Created by lny on 2017/7/24.
 */
interface RankingContract {

    interface View : EmptyView, ErrorView, ProgressView {

        fun setData(results: List<Ranking>)
    }

    interface Model {

        fun getData(): Observable<List<Ranking>>
    }

    interface Presenter : IPresenter {

        fun getData()
    }
}