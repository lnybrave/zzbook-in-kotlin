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
    override fun initData(limit: Int) {
        val subscribe = mModel.getData(limit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe { mView.onLoadStart(this@RecommendationPresenter) }
                .subscribe({
                    res ->
                    if (res.isEmpty()) {
                        mView.onEmpty(this@RecommendationPresenter)
                    } else {
                        mView.setBannerList(res.banners)
                        mView.setMenuList(res.menus)
                        mView.setData(res.page)
                        mView.onLoadStop(this@RecommendationPresenter)
                    }
                }, { e ->
                    Log.e("lny", e.message)
                    mView.onError(this@RecommendationPresenter, e.message)
                })
        addDisposable(subscribe)
    }

    override fun getData(offset: Int, limit: Int) {
        val subscribe = mModel.getData(offset, limit)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { mView.onLoadStart(this@RecommendationPresenter) }
                .subscribe({
                    res ->
                    if (res.results.isEmpty()) {
                        mView.onEmpty(this@RecommendationPresenter)
                    } else {
                        mView.setData(res)
                        mView.onLoadStop(this@RecommendationPresenter)
                    }
                }, { e ->
                    Log.e("lny", e.message)
                    mView.onError(this@RecommendationPresenter, e.message)
                })
        addDisposable(subscribe)
    }
}