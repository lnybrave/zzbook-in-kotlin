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
@Inject constructor(private val mDetailModel: ColumnDetailModel,
                    private val mView: ColumnDetailContract.View)
    : ColumnDetailContract.Presenter, BasePresenter() {
    override fun getData(id: Int, offset: Int, limit: Int) {
        val subscribe = mDetailModel.getData(id, offset, limit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    res ->
                    mView.setData(res)
                }, { e -> Log.e("lny", e.message) })
        addDisposable(subscribe)
    }
}