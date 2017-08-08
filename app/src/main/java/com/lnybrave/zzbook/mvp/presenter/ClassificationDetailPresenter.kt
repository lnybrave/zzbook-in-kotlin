package com.lnybrave.zzbook.mvp.presenter

import android.util.Log
import com.lnybrave.zzbook.mvp.contract.ClassificationDetailContract
import com.lnybrave.zzbook.mvp.model.ClassificationDetailModel
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

/**
 * Created by lny on 2017/7/24.
 */
class ClassificationDetailPresenter
@Inject constructor(private val mModel: ClassificationDetailModel,
                    private val mView: ClassificationDetailContract.View)
    : ClassificationDetailContract.Presenter, BasePresenter() {

    override fun getAll(firstId: Int, page: Int) {
        mModel.getAll(firstId, page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    res ->
                    mView.setData(res)
                }, { e -> Log.e("lny", e.message) })
    }

    override fun getData(secondId: Int, page: Int) {
        mModel.getData(secondId, page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    res ->
                    mView.setData(res)
                }, { e -> Log.e("lny", e.message) })
    }

}