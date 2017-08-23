package com.lnybrave.zzbook.mvp.model

import com.lnybrave.zzbook.api.ZZBookApi
import com.lnybrave.zzbook.bean.APIBase
import com.lnybrave.zzbook.bean.Book
import com.lnybrave.zzbook.mvp.contract.BookDetailContract
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by lny on 2017/7/24.
 */
class BookDetailModel
@Inject constructor(private val api: ZZBookApi) : BookDetailContract.Model {
    override fun getData(id: Int): Observable<Book> {
        return api.getBookDetail(id)
    }
}