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
    fun getBannerList(): Observable<List<Banner>>

    @GET("api/book/{id}")
    fun getBookDetail(@Path("id") id: Int): Observable<APIBase<Book>>

    @GET("api/bookshelf")
    fun getBookshelf(): Observable<List<Book>>

    @GET("api/subject/recommendation")
    fun getSubjectRecommendation(): Observable<List<Subject>>

    @GET("api/column/recommendation")
    fun getRecommendation(): Observable<List<Topic>>

    @GET("api/column")
    fun getColumnList(): Observable<APIList<Column>>

    @GET("api/column/{id}")
    fun getColumnDetail(@Path("id") id: Int): Observable<List<Topic>>

    @GET("api/column/topic/{id}")
    fun getColumnTopic(@Path("id") id: Int): Observable<APIList<Book>>

    @GET("api/ranking")
    fun getRankingList(): Observable<List<Ranking>>

    @GET("api/ranking")
    fun getRankingFirst(): Observable<List<Ranking>>

    @GET("api/ranking/{id}")
    fun getRankingDetail(@Path("id") id: Int, @Query("page") page: Int): Observable<Ranking>

    @GET("api/classification")
    fun getClassificationList(): Observable<List<Classification>>

    @GET("api/classification/{id}/all")
    fun getClassificationAll(@Path("id") id: Int,
                             @Query("page") page: Int): Observable<List<Book>>

    @GET("api/classification/{id}/detail")
    fun getClassificationDetail(@Path("id") id: Int,
                                @Query("page") page: Int): Observable<List<Book>>

    @GET("api/search")
    fun getSearch(@Query("search") search: String,
                  @Query("limit") limit: Int,
                  @Query("offset") offset: Int): Observable<APIPage<Book>>

    @GET("api/search/suggest")
    fun getSearchSuggest(@Query("word") content: String): Observable<List<SearchWord>>

    @GET("api/search/words")
    fun getSearchWords(): Observable<List<SearchWord>>
}


