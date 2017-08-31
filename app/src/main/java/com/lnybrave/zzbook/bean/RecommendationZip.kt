package com.lnybrave.zzbook.bean

/**
 * Created by lny on 2017/8/22.
 */
class RecommendationZip(val banners: List<Banner>,
                        val menus: List<StackMenu>,
                        val page: APIPage<MixedBean>) {
    fun isEmpty(): Boolean {
        if (banners.isEmpty()
                && menus.isEmpty()
                && !page.hasPrev() && page.results.isEmpty()) {
            return true
        }
        return false
    }
}