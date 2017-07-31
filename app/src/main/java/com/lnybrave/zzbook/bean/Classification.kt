package com.lnybrave.zzbook.bean

/**
 * Created by lny on 2017/7/24.
 */
data class Classification(val id: Int, val name: String, val icon: String, val children: List<Classification>) {

    var level: Int = 0

    fun getChildrenList(): String {
        var s: String = ""
        for ((i, item) in children.withIndex()) {
            if (i != 0) {
                s += "|"
            }
            s += item.name
        }
        return s
    }
}