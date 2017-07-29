package com.lnybrave.zzbook.bean


/**
 * Created by lny on 2017/7/28.
 */
data class Recommendation(
        val banners: List<Banner>,
        val subjects: List<Subject>,
        val topics: List<Topic>,
        var books:List<Book>?
)