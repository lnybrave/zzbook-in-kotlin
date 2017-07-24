package com.lnybrave.zzbook.mvp.presenter

import android.util.Log
import com.lnybrave.zzbook.mvp.contract.RankingContract
import com.lnybrave.zzbook.mvp.model.RankingModel
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

/**
 * Created by lny on 2017/7/24.
 */
class RankingPresenter
@Inject constructor(private val mModel: RankingModel,
                    private val mView: RankingContract.View)
    : RankingContract.Presenter {
    override fun getData() {
        mModel.getData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    res ->
                    if (res.isSuccess()) {
                        mView.setData(res.d)
                    }
                }, { e -> Log.e("lny", e.message) })
    }
}