package com.lnybrave.zzbook.mvp

/**
 * Created by lny on 2017/8/30.
 */
interface PageLoadView {

    fun onPageLoadStart(presenter: IPresenter)

    fun onPageLoadStop(presenter: IPresenter)
}