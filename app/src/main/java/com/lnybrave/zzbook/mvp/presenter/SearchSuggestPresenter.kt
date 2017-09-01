package com.lnybrave.zzbook.mvp.presenter

import android.util.Log
import com.lnybrave.zzbook.mvp.contract.SearchSuggestContract
import com.lnybrave.zzbook.mvp.model.SearchSuggestModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * Created by lny on 2017/7/24.
 */
class SearchSuggestPresenter
@Inject constructor(private val mModel: SearchSuggestModel,
                    private val mView: SearchSuggestContract.View)
    : SearchSuggestContract.Presenter, BasePresenter() {

    private var subscribe: Disposable? = null

    override fun getData(content: String) {
        if (subscribe != null && !subscribe?.isDisposed!!) {
            subscribe?.dispose()
        }
        subscribe = mModel.getData(content)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    res ->
                    mView.setData(res)
                }, { e -> Log.e("lny", e.message) })
        addDisposable(subscribe!!)
    }
}