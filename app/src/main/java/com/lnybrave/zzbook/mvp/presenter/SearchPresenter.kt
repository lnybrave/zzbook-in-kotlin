package com.lnybrave.zzbook.mvp.presenter

import android.util.Log
import com.lnybrave.zzbook.mvp.contract.SearchContract
import com.lnybrave.zzbook.mvp.model.SearchModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by lny on 2017/7/24.
 */
class SearchPresenter
@Inject constructor(private val mModel: SearchModel,
                    private val mView: SearchContract.View)
    : SearchContract.Presenter, BasePresenter() {
    override fun getData(search: String, offset: Int, limit: Int) {
        val subscribe = mModel.getData(search, offset, limit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe { mView.onLoadStart(this@SearchPresenter) }
                .subscribe({
                    res ->
                    if (!res.hasPrev() && res.results.isEmpty()) {
                        mView.onEmpty(this@SearchPresenter)
                    } else {
                        mView.setData(res)
                        mView.onLoadStop(this@SearchPresenter)
                    }
                }, { e ->
                    Log.e("lny", e.message)
                    mView.onError(this@SearchPresenter, e.message)
                })
        addDisposable(subscribe)
    }
}