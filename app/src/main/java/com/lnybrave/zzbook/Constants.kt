package com.lnybrave.zzbook

/**
 * Created by lny on 2017/7/29.
 */


object Constants {
    val TOPIC_BANNER = 1
    val TOPIC_SIMPLE = 2
    val TOPIC_COMPLEX = 3


}

fun formatBookStatus(status: Int): String {
    var ret = ""
    when (status) {
        0 -> ret = "已完结"
        1 -> ret = "连载"
    }
    return ret
}