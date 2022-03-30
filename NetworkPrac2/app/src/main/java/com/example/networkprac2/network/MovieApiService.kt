package com.example.networkprac2.network

import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.networkprac2.network.Result

private const val BASE_URL = "https://www.kobis.or.kr"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface MovieApiService {
    @GET("/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json")
    suspend fun getProperties(
        @Query("key") key: String,
        @Query("targetDt") targetDt: String
    ): Result
}

object MovieApi {
    val movieService: MovieApiService by lazy {
        retrofit.create(MovieApiService::class.java)
    }
}

