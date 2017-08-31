package com.lnybrave.zzbook.mvp.presenter

import android.util.Log
import com.lnybrave.zzbook.mvp.contract.SearchHotContract
import com.lnybrave.zzbook.mvp.model.SearchHotModel
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

/**
 * Created by lny on 2017/7/24.
 */
class SearchHotPresenter
@Inject constructor(private val mModel: SearchHotModel,
                    private val mView: SearchHotContract.View)
    : SearchHotContract.Presenter, BasePresenter() {
    override fun getData() {
        val subscribe = mModel.getData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    res ->
                    mView.setData(res)
                }, { e -> Log.e("lny", e.message) })
        addDisposable(subscribe)
    }
}