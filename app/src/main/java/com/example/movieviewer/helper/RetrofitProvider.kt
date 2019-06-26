package com.example.movieviewer.helper

import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by Vincent on 11/10/18
 */
 object RetrofitProvider {

  private var retrofit: Retrofit? =null

  fun retrofit(): Retrofit {

    if (retrofit ==null) {
      //TODO("Initialize Retrofit")
       retrofit =  Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
    }
    return retrofit!!

  }
}