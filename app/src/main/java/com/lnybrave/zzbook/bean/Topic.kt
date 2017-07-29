package com.lnybrave.zzbook.bean

/**
 * Created by lny on 2017/7/17.
 * "pk": 5,
"name": "精选专题1",
"desc": "精选专题",
"type": 1,
 */
data class Topic(
        val id: Int,
        val name: String,
        val desc: String,
        val type: Int,
        val books: List<Book>
)