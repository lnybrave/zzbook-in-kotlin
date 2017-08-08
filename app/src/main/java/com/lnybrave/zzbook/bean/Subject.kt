package com.lnybrave.zzbook.bean

import java.io.Serializable

/**
 * Created by lny on 2017/7/28.
 */
data class Subject(val id: Int, val name: String, val type: Int, val icon: String) : Serializable {
}