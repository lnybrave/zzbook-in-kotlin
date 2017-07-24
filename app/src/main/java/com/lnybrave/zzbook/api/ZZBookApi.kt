package com.lnybrave.zzbook.api

import com.lnybrave.zzbook.entity.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by lny on 2017/7/17.
 */
interface ZZBookApi {

    @GET("api/banner")
    fun getBannerList(): Observable<ApiList<Any>>

    @GET("api/banner/bookshelf")
    fun getBannerBookshelf(): Observable<ApiList<Any>>

    @GET("api/book/{id}")
    fun getBookDetail(@Path("id") id: Int): Observable<ApiBase<Book>>

    @GET("api/bookshelf")
    fun getBookshelf(): Observable<ApiList<Book>>

    @GET("api/recommendation")
    fun getRecommendation(): Observable<ApiList<Topic>>

    @GET("api/column")
    fun getColumnList(): Observable<ApiList<Column>>

    @GET("api/column/{id}")
    fun getColumnDetail(@Path("id") id: Int): Observable<ApiList<Topic>>

    @GET("api/column/topic/{id}")
    fun getColumnTopic(@Path("id") id: Int): Observable<ApiList<Book>>

    @GET("api/ranking")
    fun getRankingList(): Observable<ApiList<Ranking>>

    @GET("api/ranking/{id}")
    fun getRankingDetail(@Path("id") id: Int, @Query("page") page: Int): Observable<ApiPage<Book>>

    @GET("api/classification")
    fun getClassificationList(): Observable<ApiList<Classification>>

    @GET("api/classification/{id}")
    fun getClassificationDetail(@Path("id") id: Int, @Query("page") page: Int): Observable<ApiPage<Book>>

    @GET("api/search")
    fun getSearch(@Query("search") search: String, @Query("offset") offset: Int): Observable<ApiList<Book>>

    @GET("api/search/keyword")
    fun getSearch(): Observable<ApiList<String>>
}


