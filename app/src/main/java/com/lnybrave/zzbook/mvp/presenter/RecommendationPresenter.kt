package com.lnybrave.zzbook.mvp.presenter

import android.util.Log
import com.lnybrave.zzbook.mvp.contract.RecommendationContract
import com.lnybrave.zzbook.mvp.model.RecommendationModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by lny on 2017/7/24.
 */
class RecommendationPresenter
@Inject constructor(private val mModel: RecommendationModel,
                    private val mView: RecommendationContract.View)
    : RecommendationContract.Presenter, BasePresenter() {
    override fun getData() {
        val subscribe = mModel.getData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe { mView.onBegin(this@RecommendationPresenter) }
                .doOnComplete { mView.onEnd(this@RecommendationPresenter) }
                .subscribe({
                    res ->
                    mView.setBannerList(res.banners)
                    mView.setMenuList(res.menus)
                    mView.setData(res.page)
                }, { e ->
                    Log.e("lny", e.message)
                    mView.onError(this@RecommendationPresenter, e.message)
                })
        addDisposable(subscribe)
    }

    override fun getData(offset: Int, limit: Int) {
        val subscribe = mModel.getData(offset, limit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    res ->
                    mView.setData(res)
                }, { e ->
                    Log.e("lny", e.message)
                    mView.onError(this@RecommendationPresenter, e.message)
                })
        addDisposable(subscribe)
    }
}