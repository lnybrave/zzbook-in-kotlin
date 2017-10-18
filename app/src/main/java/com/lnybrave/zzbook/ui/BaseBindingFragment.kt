package com.lnybrave.zzbook.ui

import android.databinding.ViewDataBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


abstract class BaseBindingFragment<B : ViewDataBinding> : ProgressFragment() {

    lateinit var mBinding: B

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mBinding = createDataBinding(inflater, container, savedInstanceState)
        return mBinding.root

    }

    abstract fun createDataBinding(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): B

}