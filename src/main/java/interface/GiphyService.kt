package com.example.giphy2

import dataclass.GiphyResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyService {
    @GET("gifs/search")
    suspend fun searchGifs(
        @Query("api_key") apiKey: String,
        @Query("q") query: String,
        @Query("limit") limit: Int = 25,
        @Query("offset") offset: Int = 0,
        @Query("rating") rating: String = "g",
        @Query("lang") lang: String = "en"
    ): GiphyResponse
}





