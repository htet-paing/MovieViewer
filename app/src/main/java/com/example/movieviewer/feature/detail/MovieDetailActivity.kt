package com.example.movieviewer.feature.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.movieviewer.R
import com.example.movieviewer.model.Movie

/**
 * Created by Vincent on 11/10/18
 */
class MovieDetailActivity : AppCompatActivity() {

  /**
   * Convenient helper function to easily pass data between activity
   */
  companion object {
    private const val IE_MOVIE_ID = "IE_MOVIE_ID"

    fun newIntent(context: Context, movieId: Long): Intent {
      val intent = Intent(context, MovieDetailActivity::class.java)
      intent.putExtra(IE_MOVIE_ID, movieId)
      return intent
    }

  }

  private val movieId by lazy {
    intent.getLongExtra(IE_MOVIE_ID, -1)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_movie_detail)

    title = "Movie Detail"
    supportActionBar?.setDisplayHomeAsUpEnabled(true)

    loadData()
  }

  private fun loadData() {
    TODO("Load data from API")
  }

  private fun showLoading() {
    TODO("Show loading view")
  }

  private fun showData(movie: Movie) {
    TODO("Show Data")
  }

  private fun showError() {
    TODO("Show error")
  }

}

