package com.lnybrave.zzbook.mvp.presenter

import android.util.Log
import com.lnybrave.zzbook.mvp.contract.ClassificationDetailContract
import com.lnybrave.zzbook.mvp.contract.ColumnDetailContract
import com.lnybrave.zzbook.mvp.model.ClassificationDetailModel
import com.lnybrave.zzbook.mvp.model.ColumnDetailModel
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

/**
 * Created by lny on 2017/7/24.
 */
class ClassificationDetailPresenter
@Inject constructor(private val mModel: ClassificationDetailModel,
                    private val mView: ClassificationDetailContract.View)
    : ClassificationDetailContract.Presenter {
    override fun getData(id: Int, page: Int) {
        mModel.getData(id, page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    res ->
                    if (res.isSuccess()) {
                        mView.setData(res.d)
                    }
                }, { e -> Log.e("lny", e.message) })
    }
}