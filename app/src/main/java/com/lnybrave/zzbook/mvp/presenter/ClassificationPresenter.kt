package com.lnybrave.zzbook.mvp.presenter

import android.util.Log
import com.lnybrave.zzbook.mvp.contract.ClassificationContract
import com.lnybrave.zzbook.mvp.model.ClassificationModel
import io.reactivex.android.schedulers.AndroidSchedulers
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
                .subscribe({
                    res ->
                    mView.setData(res)
                }, { e -> Log.e("lny", e.message) })
    }
}