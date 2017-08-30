package com.lnybrave.zzbook.mvp

/**
 * Created by lny on 2017/8/30.
 */
interface ProgressView {
    fun onBegin(presenter: IPresenter)

    fun onEnd(presenter: IPresenter)
}