package com.lnybrave.zzbook.mvp.presenter

import android.util.Log
import com.lnybrave.zzbook.mvp.contract.SearchContract
import com.lnybrave.zzbook.mvp.model.SearchModel
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

/**
 * Created by lny on 2017/7/24.
 */
class SearchPresenter
@Inject constructor(private val mModel: SearchModel,
                    private val mView: SearchContract.View)
    : SearchContract.Presenter, BasePresenter() {
    override fun getData(search: String, limit: Int, offset: Int) {
        mModel.getData(search, limit, offset)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    res ->
                    mView.setData(res)
                }, { e -> Log.e("lny", e.message) })
    }
}