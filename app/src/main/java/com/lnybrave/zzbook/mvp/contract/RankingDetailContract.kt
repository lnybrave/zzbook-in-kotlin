package com.lnybrave.zzbook.mvp.contract

import com.lnybrave.zzbook.bean.Ranking
import io.reactivex.Observable

/**
 * Created by lny on 2017/7/24.
 */
interface RankingDetailContract {

    interface View {

        fun setData(results: Ranking)
    }

    interface Model {

        fun getData(id: Int, page: Int): Observable<Ranking>
    }

    interface Presenter {

        fun getData(id: Int, page: Int)
    }
}