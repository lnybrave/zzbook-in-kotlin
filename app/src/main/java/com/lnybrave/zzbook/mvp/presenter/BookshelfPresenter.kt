package com.lnybrave.zzbook.mvp.presenter

import android.util.Log
import com.lnybrave.zzbook.mvp.contract.BookshelfContract
import com.lnybrave.zzbook.mvp.model.BookshelfModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by lny on 2017/7/24.
 */
class BookshelfPresenter
@Inject constructor(private val mModel: BookshelfModel,
                    private val mView: BookshelfContract.View)
    : BookshelfContract.Presenter, BasePresenter() {
    override fun getData() {
        mModel.getData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe { mView.onLoadStart(this@BookshelfPresenter) }
                .subscribe({
                    res ->
                    mView.setData(res)
                    mView.onLoadStop(this@BookshelfPresenter)
                }, { e ->
                    Log.e("lny", e.message)
                    mView.onError(this@BookshelfPresenter, e.message)
                })
    }
}