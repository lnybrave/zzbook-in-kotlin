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
    : RankingDetailContract.Presenter, BasePresenter() {
    override fun getData(id: Int) {
        mModel.getData(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    res ->
                    if (res.isNotEmpty()) {
                        mView.setData(res)
                    } else {
                        mView.onEmpty(this@RankingDetailPresenter)
                    }
                }, { e ->
                    Log.e("lny", e.message)
                    mView.onError(this@RankingDetailPresenter, e.message)
                })
    }
}