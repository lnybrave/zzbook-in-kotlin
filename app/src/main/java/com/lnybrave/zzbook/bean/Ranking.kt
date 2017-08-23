package com.lnybrave.zzbook.bean

/**
 * Created by lny on 2017/7/24.
 */
data class Ranking(
        val id: Int,
        val name: String,
        val books: List<Book>,
        val children: List<Ranking>?
)