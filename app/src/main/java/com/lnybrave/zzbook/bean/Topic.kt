package com.lnybrave.zzbook.bean

/**
 * Created by lny on 2017/7/17.
 */
data class Topic(
        val id: Int,
        val name: String,
        val desc: String,
        val type: Int,
        val books: List<Book>?
)