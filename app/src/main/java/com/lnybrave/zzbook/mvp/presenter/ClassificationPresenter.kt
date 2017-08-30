package com.lnybrave.zzbook.mvp.presenter

import android.util.Log
import com.lnybrave.zzbook.mvp.contract.ClassificationContract
import com.lnybrave.zzbook.mvp.model.ClassificationModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by lny on 2017/7/24.
 */
class ClassificationPresenter
@Inject constructor(private val mModel: ClassificationModel,
                    private val mView: ClassificationContract.View)
    : ClassificationContract.Presenter, BasePresenter() {
    override fun getData() {
        mModel.getData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    res ->
                    if (res.isNotEmpty()) {
                        mView.setData(res)
                    } else {
                        mView.onEmpty(this@ClassificationPresenter)
                    }
                }, { e ->
                    Log.e("lny", e.message)
                    mView.onError(this@ClassificationPresenter, e.message)
                })
    }
}