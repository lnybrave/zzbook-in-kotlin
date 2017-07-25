package com.lnybrave.zzbook.bean

/**
 * Created by lny on 2017/7/17.
 */
data class APIPage<out T>(
        val c: Int,
        val m: String,
        val d: List<T>,
        val size: Int,
        val max: Int
) {
    fun isSuccess(): Boolean {
        return c == 0
    }
}