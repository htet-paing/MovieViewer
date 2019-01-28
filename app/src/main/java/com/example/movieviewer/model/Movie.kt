package com.example.movieviewer.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by Vincent on 11/10/18
 */
//TODO("Add Other fields and moshi annotations")
@JsonClass(generateAdapter = true)
data class Movie(
  @Json(name = "id") val id: Long,
  @Json(name = "poster_path") val posterPath: String?
)