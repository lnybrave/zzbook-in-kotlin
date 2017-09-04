package com.lnybrave.zzbook

/**
 * Created by lny on 2017/7/29.
 */
val DOMAIN = "http://106.15.206.82/"

fun getDomain(): String = if (BuildConfig.DEBUG) "http://192.168.1.159:8000/" else DOMAIN

internal object Constants {
    val TOPIC_SIMPLE = 1
    val TOPIC_COMPLEX = 2
}