package com.lnybrave.zzbook.mvp.contract

import com.lnybrave.zzbook.bean.Ranking
import io.reactivex.Observable

/**
 * Created by lny on 2017/7/24.
 */
interface RankingContract {

    interface View {

        fun setData(results: List<Ranking>)
    }

    interface Model {

        fun getData(): Observable<List<Ranking>>
    }

    interface Presenter {

        fun getData()
    }
}