package com.lnybrave.zzbook.mvp.presenter

import android.util.Log
import com.lnybrave.zzbook.mvp.contract.RankingDetailContract
import com.lnybrave.zzbook.mvp.model.RankingDetailModel
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

/**
 * Created by lny on 2017/7/24.
 */
class RankingDetailPresenter
@Inject constructor(private val mModel: RankingDetailModel,
                    private val mView: RankingDetailContract.View)
    : RankingDetailContract.Presenter {
    override fun getData(id: Int, page: Int) {
        mModel.getData(id, page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    res ->
                    if (res.isSuccess()) {
                        mView.setData(res.d)
                    }
                }, { e -> Log.e("lny", e.message) })
    }
}