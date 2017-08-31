package com.lnybrave.zzbook.mvp

/**
 * Created by lny on 2017/8/30.
 */
interface LoadView {

    fun onLoadStart(presenter: IPresenter)

    fun onLoadStop(presenter: IPresenter)
}