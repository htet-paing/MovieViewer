package com.example.movieviewer.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Vincent on 11/10/18
 */
public interface MovieService {
  //TODO("API CALL")
    @GET("movie/popular")
    fun popular(@Query("api_key") apiKey: String): Call<MovieResponse>

}
