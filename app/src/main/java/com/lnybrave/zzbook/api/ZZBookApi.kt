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
    fun getBookDetail(@Path("id") id: Int): Observable<Book>

    @GET("api/bookshelf")
    fun getBookshelf(): Observable<List<Book>>

    @GET("api/bookshelf/banner")
    fun getBookshelfBanner(): Observable<List<Banner>>

    @GET("api/stack/menu")
    fun getStackMenu(): Observable<List<StackMenu>>

    @GET("api/stack/recommendation")
    fun getRecommendation(@Query("offset") offset: Int?,
                          @Query("limit") limit: Int?): Observable<APIPage<MixedBean>>

    @GET("api/stack/column")
    fun getColumn(): Observable<List<Column>>

    @GET("api/stack/column/{id}/detail")
    fun getColumnDetail(@Path("id") id: Int,
                        @Query("offset") offset: Int?,
                        @Query("limit") limit: Int?): Observable<APIPage<MixedBean>>

    @GET("api/stack/topic/{id}")
    fun getTopicDetail(@Path("id") id: Int): Observable<APIList<Book>>

    @GET("api/stack/ranking")
    fun getRanking(@Query("parent") parent: Int?): Observable<List<Ranking>>

    @GET("api/stack/ranking/with_books")
    fun getRankingWithBooks(@Query("parent") parent: Int?): Observable<List<Ranking>>

    @GET("api/stack/ranking/{id}/books")
    fun getRankingBooks(@Path("id") id: Int,
                        @Query("offset") offset: Int?,
                        @Query("limit") limit: Int?): Observable<APIPage<Book>>

    @GET("api/stack/classification")
    fun getClassification(@Query("parent") parent: Int?): Observable<List<Classification>>

    @GET("api/stack/classification/{parent_id}/{id}/books")
    fun getClassificationBooks(@Path("parent_id") parentId: Int,
                               @Path("id") id: Int,
                               @Query("offset") offset: Int?,
                               @Query("limit") limit: Int?): Observable<APIPage<Book>>

    @GET("api/search")
    fun getSearch(@Query("search") search: String,
                  @Query("offset") offset: Int,
                  @Query("limit") limit: Int): Observable<APIPage<Book>>

    @GET("api/search/suggest")
    fun getSearchSuggest(@Query("word") content: String): Observable<List<SearchWord>>

    @GET("api/search/top/{count}")
    fun getSearchTop(@Path("count") count: Int): Observable<List<Book>>

    @GET("api/search/words")
    fun getSearchWords(): Observable<List<SearchWord>>
}


