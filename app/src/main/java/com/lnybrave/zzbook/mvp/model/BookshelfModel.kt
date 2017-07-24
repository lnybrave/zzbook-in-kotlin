package com.lnybrave.zzbook.mvp.model

import com.lnybrave.zzbook.api.ZZBookApi
import com.lnybrave.zzbook.entity.Book
import com.lnybrave.zzbook.entity.ApiList
import com.lnybrave.zzbook.mvp.contract.BookshelfContract
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by lny on 2017/7/24.
 */
class BookshelfModel
@Inject constructor(private val api: ZZBookApi) : BookshelfContract.Model {
    override fun getData(): Observable<ApiList<Book>> {
        return api.getBookshelf()
    }
}