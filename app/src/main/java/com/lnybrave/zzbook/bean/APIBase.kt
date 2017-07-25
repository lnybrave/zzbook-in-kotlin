package com.lnybrave.zzbook.bean

/**
 * Created by lny on 2017/7/17.
 */
data class APIBase<out T>(
        val c: Int,
        val m: String,
        val d: T
) {

    fun isSuccess(): Boolean {
        return c == 0
    }
}