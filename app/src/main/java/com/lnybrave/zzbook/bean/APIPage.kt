package com.lnybrave.zzbook.bean

/**
 * Created by lny on 2017/7/17.
 *
"count": 2,
"next": "http://192.168.1.159:8000/api/search/?limit=1&offset=1&search=%E5%93%81",
"previous": null,
"results": [T]
 */
data class APIPage<out T>(
        val results: List<T>,
        val count: Int,
        var next: String,
        var previous: String
) {
    fun hasNext(): Boolean = next != null

    fun hasPrev(): Boolean = previous != null
}