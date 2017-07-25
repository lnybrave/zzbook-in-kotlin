package com.lnybrave.zzbook.api

import com.lnybrave.zzbook.bean.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by lny on 2017/7/17.
 */
interface ZZBookApi {

    @GET("api/banner")
    fun getBannerList(): Observable<APIList<Banner>>

    @GET("api/book/{id}")
    fun getBookDetail(@Path("id") id: Int): Observable<APIBase<Book>>

    @GET("api/bookshelf")
    fun getBookshelf(): Observable<List<Book>>

    @GET("api/recommendation")
    fun getRecommendation(): Observable<APIList<Topic>>

    @GET("api/column")
    fun getColumnList(): Observable<APIList<Column>>

    @GET("api/column/{id}")
    fun getColumnDetail(@Path("id") id: Int): Observable<APIList<Topic>>

    @GET("api/column/topic/{id}")
    fun getColumnTopic(@Path("id") id: Int): Observable<APIList<Book>>

    @GET("api/ranking")
    fun getRankingList(): Observable<APIList<Ranking>>

    @GET("api/ranking/{id}")
    fun getRankingDetail(@Path("id") id: Int, @Query("page") page: Int): Observable<APIPage<Book>>

    @GET("api/classification")
    fun getClassificationList(): Observable<APIList<Classification>>

    @GET("api/classification/{id}")
    fun getClassificationDetail(@Path("id") id: Int, @Query("page") page: Int): Observable<APIPage<Book>>

    @GET("api/search")
    fun getSearch(@Query("search") search: String, @Query("offset") offset: Int): Observable<APIList<Book>>

    @GET("api/search/keyword")
    fun getSearch(): Observable<APIList<String>>
}


