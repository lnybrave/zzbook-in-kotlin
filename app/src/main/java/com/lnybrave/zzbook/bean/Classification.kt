package com.lnybrave.zzbook.bean

import java.io.Serializable

/**
 * Created by lny on 2017/7/24.
 */
data class Classification(
        val id: Int,
        val name: String,
        val icon: String,
        val books: List<Book>,
        val children: List<Classification>
) : Serializable {

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

    constructor(id: Int, name: String) : this(id, name, "", ArrayList<Book>(), ArrayList<Classification>())
}