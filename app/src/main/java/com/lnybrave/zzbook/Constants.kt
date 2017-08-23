package com.lnybrave.zzbook

/**
 * Created by lny on 2017/7/29.
 */


val DOMAIN = "http://106.15.206.82/"

fun getDomain(): String = if (BuildConfig.DEBUG) "http://192.168.1.161:8000/" else DOMAIN


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