package com.lnybrave.zzbook.mvp.presenter

import android.util.Log
import com.lnybrave.zzbook.mvp.contract.SearchSuggestContract
import com.lnybrave.zzbook.mvp.contract.SearchWordContract
import com.lnybrave.zzbook.mvp.model.SearchSuggestModel
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

/**
 * Created by lny on 2017/7/24.
 */
class SearchSuggestPresenter
@Inject constructor(private val mModel: SearchSuggestModel,
                    private val mView: SearchSuggestContract.View)
    : SearchSuggestContract.Presenter, BasePresenter() {
    override fun getData(content: String) {
        mModel.getData(content)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    res ->
                    mView.setData(res)
                }, { e -> Log.e("lny", e.message) })
    }
}