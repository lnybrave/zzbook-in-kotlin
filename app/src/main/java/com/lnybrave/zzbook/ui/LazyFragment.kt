package com.lnybrave.zzbook.ui

import android.os.Bundle


/**
 * Created by lny on 2017/9/14.
 */
abstract class LazyFragment : BaseFragment() {

    private var isPrepared: Boolean = false
    private var isFirstStart: Boolean = true
    private var isFirstVisible: Boolean = true
    private var isFirstInvisible: Boolean = true

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initPrepare()
    }

    override fun onStart() {
        super.onStart()
        if (isFirstStart) {
            isFirstStart = false
            return
        }
        if (userVisibleHint) {
            onUserVisible(false)
        }
    }

    override fun onPause() {
        super.onPause()
        if (userVisibleHint) {
            setUserInvisible()
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            setUserVisible()
        } else if (isPrepared) {
            setUserInvisible()
        }
    }

    private fun setUserVisible() {
        if (isFirstVisible) {
            isFirstVisible = false
            initPrepare()
        } else {
            onUserVisible(false)
        }
    }

    private fun setUserInvisible() {
        if (isFirstInvisible) {
            isFirstInvisible = false
            onUserInvisible(true)
        } else {
            onUserInvisible(false)
        }
    }

    private fun initPrepare() {
        if (isPrepared) {
            onUserVisible(true)
        } else {
            isPrepared = true
        }
    }

    /**
     * fragment可见（切换回来或者onResume）
     */
    open fun onUserVisible(firstVisible: Boolean) {

    }

    /**
     * fragment不可见（切换掉或者onPause）
     */
    open fun onUserInvisible(firstInvisible: Boolean) {

    }
}