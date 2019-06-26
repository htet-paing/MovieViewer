package com.example.movieviewer.feature.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.movieviewer.R
import com.example.movieviewer.helper.GlideApp
import com.example.movieviewer.helper.RetrofitProvider
import com.example.movieviewer.model.Movie
import com.example.movieviewer.model.MovieService
import kotlinx.android.synthetic.main.activity_browse.*
import kotlinx.android.synthetic.main.activity_movie_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

    showLoading()
    val movieService = RetrofitProvider.retrofit().create(MovieService::class.java)
    if(movieId != -1L){
        movieService.detail(movieId,"1611e2ffe746c99b86236930d02c2f2e").enqueue(object : Callback<Movie>{
            override fun onFailure(call: Call<Movie>, t: Throwable) {
                t?.printStackTrace()
                showError()
            }

            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if(response.isSuccessful){
                    val movie = response.body()!!
                    showData(movie)
                }else{
                    showError()
                }

            }

        })
    }else
    {
        showError()

    }
  }

  private fun showLoading() {
      progresBar.visibility = View.VISIBLE
      groupData.visibility = View.GONE
  }

  private fun showData(movie: Movie) {
      progresBar.visibility = View.GONE
      groupData.visibility = View.VISIBLE
      tvTitle.text = movie.title
      tvOverView.text = movie.overView

      val fullBackDropPath = "https://image.tmdb.org/t/p/original/${movie.backdropPath}"
      GlideApp.with(this)
          .load(fullBackDropPath)
          .placeholder(R.drawable.placeholder_bg)
          .centerCrop()
          .into(ivBackDrop)


      val fullPosterPath = "https://image.tmdb.org/t/p/original/${movie.posterPath}"
      GlideApp.with(this)
          .load(fullPosterPath)
          .placeholder(R.drawable.placeholder_bg)
          .centerCrop()
          .into(ivPoster)


  }

  private fun showError() {
      progressBar.visibility = View.GONE
      rvMovie.visibility = View.GONE

      val snackBar = Snackbar.make(viewRoot, "Error loading data", Snackbar.LENGTH_INDEFINITE)

      snackBar.setAction("Retry") {
          loadData()
          snackBar.dismiss()
      }

      snackBar.show()
  }

}



