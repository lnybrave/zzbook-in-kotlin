package com.lnybrave.zzbook.entity

/**
 * Created by lny on 2017/7/17.
 */
data class ApiList<out T>(
        val c: Int,
        val m: String,
        val d: List<T>
) {
    fun isSuccess(): Boolean {
        return c == 0
    }
}