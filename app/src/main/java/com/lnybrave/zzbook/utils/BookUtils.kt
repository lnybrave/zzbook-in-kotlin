package com.lnybrave.zzbook.utils

/**
 * Created by lny on 2017/8/31.
 */
object BookUtils {

    fun formatStatus(status: Int): String {
        when (status) {
            0 -> return "连载"
            1 -> return "完结"
            else ->
                return "未知"
        }
    }

    fun formatWordSize(size: String): String {
        return size + "字"
    }
}