package com.lnybrave.zzbook.mvp.presenter

import android.util.Log
import com.lnybrave.zzbook.mvp.contract.BookDetailContract
import com.lnybrave.zzbook.mvp.model.BookDetailModel
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

/**
 * Created by lny on 2017/7/24.
 */
class BookDetailPresenter
@Inject constructor(private val mModel: BookDetailModel,
                    private val mView: BookDetailContract.View)
    : BookDetailContract.Presenter {
    override fun getData(id: Int) {
        mModel.getData(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    res ->
                    mView.setData(res)
                }, { e ->
                    Log.e("lny", e.message)
                    mView.onError(this@BookDetailPresenter, e.message)
                })
    }
}