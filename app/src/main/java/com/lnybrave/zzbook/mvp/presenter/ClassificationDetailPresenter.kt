package com.lnybrave.zzbook.mvp.presenter

import android.util.Log
import com.lnybrave.zzbook.mvp.contract.ClassificationDetailContract
import com.lnybrave.zzbook.mvp.model.ClassificationDetailModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by lny on 2017/7/24.
 */
class ClassificationDetailPresenter
@Inject constructor(private val mModel: ClassificationDetailModel,
                    private val mView: ClassificationDetailContract.View)
    : ClassificationDetailContract.Presenter, BasePresenter() {

    override fun getData(firstId: Int, secondId: Int, offset: Int, limit: Int) {
        val subscribe = mModel.getData(firstId, secondId, offset, limit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe { mView.onLoadStart(this@ClassificationDetailPresenter) }
                .subscribe({
                    res ->
                    if (!res.hasPrev() && res.results.isEmpty()) {
                        mView.onEmpty(this@ClassificationDetailPresenter)
                    } else {
                        mView.setData(res)
                        mView.onLoadStop(this@ClassificationDetailPresenter)
                    }
                }, { e -> Log.e("lny", e.message) })
        addDisposable(subscribe)
    }

}