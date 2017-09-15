package com.lnybrave.zzbook.bean

/**
 * Created by lny on 2017/7/17.
 */
open class APIBase<T>(
        val c: Int = 0,
        val m: String? = null,
        val d: T? = null
) {
    fun isSuccess(): Boolean {
        return c == 0
    }
}