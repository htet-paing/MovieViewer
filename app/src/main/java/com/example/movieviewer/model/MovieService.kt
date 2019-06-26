package com.example.movieviewer.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Vincent on 11/10/18
 */
interface MovieService {
  //TODO("API CALL")
    @GET("movie/popular")
    fun popular(@Query("api_key") apiKey: String): Call<MovieResponse>

    @GET("movie/{movie_id}")
    fun detail(@Path("movie_id") movieId: Long, @Query("api_key") apikey: String)
  :Call<Movie>
}
