package com.lnybrave.zzbook.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by lny on 2017/7/17.
 */
data class Book(
        val id: Int,
        val name: String,
        val brief: String,
        val desc: String,
        @SerializedName(value = "cover_url")
        val coverUrl: String,
        @SerializedName(value = "cover_url_small")
        val coverUrlSmall: String,
        val status: Int,
        @SerializedName(value = "first_cid")
        val firstCid: String,
        @SerializedName(value = "last_cid")
        val lastCid: String,
        @SerializedName(value = "chapter_size")
        val chapterSize: Int,
        val score: Float,
        @SerializedName(value = "word_size")
        val wordSize: String,
        @SerializedName(value = "click_amount")
        var clickAmount: Int,
        val price: Int,
        @SerializedName(value = "charge_mode")
        val chargeMode: Int,
        var showType: Int
) : Serializable