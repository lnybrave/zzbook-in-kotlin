package com.lnybrave.zzbook.mvp.presenter

import android.util.Log
import com.lnybrave.zzbook.mvp.contract.RankingContract
import com.lnybrave.zzbook.mvp.model.RankingModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by lny on 2017/7/24.
 */
class RankingPresenter
@Inject constructor(private val mModel: RankingModel,
                    private val mView: RankingContract.View)
    : RankingContract.Presenter, BasePresenter() {
    override fun getData() {
        val subscribe = mModel.getData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe { mView.onLoadStart(this@RankingPresenter) }
                .doOnComplete { }
                .subscribe({
                    res ->
                    if (res.isEmpty()) {
                        mView.onEmpty(this@RankingPresenter)
                    } else {
                        mView.setData(res)
                        mView.onLoadStop(this@RankingPresenter)
                    }
                }, { e ->
                    Log.e("lny", e.message)
                    mView.onError(this@RankingPresenter, e.message)
                })
        addDisposable(subscribe)
    }
}