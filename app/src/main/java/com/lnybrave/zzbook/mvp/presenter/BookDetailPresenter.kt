package com.lnybrave.zzbook.mvp.presenter

import android.util.Log
import com.lnybrave.zzbook.mvp.contract.BookDetailContract
import com.lnybrave.zzbook.mvp.model.BookDetailModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by lny on 2017/7/24.
 */
class BookDetailPresenter
@Inject constructor(private val mModel: BookDetailModel,
                    private val mView: BookDetailContract.View)
    : BookDetailContract.Presenter, BasePresenter() {
    override fun getData(id: Int) {
        val subscribe = mModel.getData(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe { mView.onLoadStart(this@BookDetailPresenter) }
                .subscribe({
                    res ->
                    mView.setData(res)
                    mView.onLoadStop(this@BookDetailPresenter)
                }, { e ->
                    Log.e("lny", e.message)
                    mView.onError(this@BookDetailPresenter, e.message)
                })
        addDisposable(subscribe)
    }
}