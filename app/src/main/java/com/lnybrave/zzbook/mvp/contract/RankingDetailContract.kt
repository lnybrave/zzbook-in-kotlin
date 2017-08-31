package com.lnybrave.zzbook.mvp.contract

import com.lnybrave.zzbook.bean.Ranking
import com.lnybrave.zzbook.mvp.EmptyView
import com.lnybrave.zzbook.mvp.ErrorView
import com.lnybrave.zzbook.mvp.IPresenter
import com.lnybrave.zzbook.mvp.LoadView
import io.reactivex.Observable

/**
 * Created by lny on 2017/7/24.
 */
interface RankingDetailContract {

    interface View : EmptyView, ErrorView, LoadView {

        fun setData(data: List<Ranking>)
    }

    interface Model {

        fun getData(id: Int): Observable<List<Ranking>>
    }

    interface Presenter : IPresenter {

        fun getData(id: Int)
    }
}