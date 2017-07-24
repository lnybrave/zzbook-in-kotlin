package com.lnybrave.zzbook.mvp.presenter

import android.util.Log
import com.lnybrave.zzbook.mvp.contract.ColumnDetailContract
import com.lnybrave.zzbook.mvp.model.ColumnDetailModel
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

/**
 * Created by lny on 2017/7/24.
 */
class ColumnDetailPresenter
@Inject constructor(private val mModel: ColumnDetailModel,
                    private val mView: ColumnDetailContract.View)
    : ColumnDetailContract.Presenter {
    override fun getData(id: Int) {
        mModel.getData(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    res ->
                    if (res.isSuccess()) {
                        mView.setData(res.d)
                    }
                }, { e -> Log.e("lny", e.message) })
    }
}