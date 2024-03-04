package com.example.sarahkhan_newsapp


import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {
    @GET("v2/top-headlines")
    fun getTopHeadlines(
        @Query("country")
        countryCode:String = "us",
        @Query("category")
        category: String?,
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = "f957522a5caf454a98125f92856e3008"
    ): Call<News>
}