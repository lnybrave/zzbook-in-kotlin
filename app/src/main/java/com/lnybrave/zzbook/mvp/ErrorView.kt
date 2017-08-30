package com.lnybrave.zzbook.mvp

interface ErrorView {
    fun onError(presenter: IPresenter, message: String?)
}