package com.lnybrave.zzbook.di.module

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.lnybrave.zzbook.api.ZZBookApi
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File


@Module
class ApiModule {

    @Provides fun provideRetrofit(baseUrl: HttpUrl, client: OkHttpClient, gson: Gson) =
            Retrofit.Builder()
                    .client(client)
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                    .build()

    @Provides fun provideBaseUrl() = HttpUrl.parse("http://192.168.1.159:8000/")

    @Provides fun provideOkhttp(context: Context, interceptor: HttpLoggingInterceptor): OkHttpClient {
        val cacheSize = 1024 * 1024 * 10L
        val cacheDir = File(context.cacheDir, "http")
        val cache = Cache(cacheDir, cacheSize)
        return OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(interceptor).build()
    }

    @Provides fun provideInterceptor() : HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor{
            msg -> Log.d("okhttp",msg)
        }
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides fun provideGson() = GsonBuilder().create()

    @Provides fun provideApi(retrofit: Retrofit) = retrofit.create(ZZBookApi::class.java)
}

