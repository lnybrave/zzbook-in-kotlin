package com.lnybrave.zzbook.bean

/**
 * Created by lny on 2017/7/17.
 */
data class APIPage<T>(
        val results: List<T>,
        val count: Int = 0,
        var next: String?,
        var previous: String?
) : APIList<T>() {

    fun hasNext(): Boolean = next != null

    fun hasPrev(): Boolean = previous != null
}