package com.lnybrave.zzbook.bean

/**
 * Created by lny on 2017/7/17.
 */
data class APIPage<out T>(
        val results: List<T>,
        val count: Int,
        var next: String?,
        var previous: String?
) {
    fun hasNext(): Boolean = next != null

    fun hasPrev(): Boolean = previous != null
}