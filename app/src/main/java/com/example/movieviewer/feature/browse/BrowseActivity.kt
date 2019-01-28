package com.example.movieviewer.feature.browse

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.example.movieviewer.R
import com.example.movieviewer.helper.RetrofitProvider
import com.example.movieviewer.model.Movie
import com.example.movieviewer.model.MovieResponse
import com.example.movieviewer.model.MovieService
import kotlinx.android.synthetic.main.activity_browse.progressBar
import kotlinx.android.synthetic.main.activity_browse.rvMovie
import kotlinx.android.synthetic.main.activity_browse.viewRoot
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BrowseActivity : AppCompatActivity() {

  private val movieRecyclerViewAdapter = MovieRecyclerViewAdapter()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_browse)
    title = "Popular"

    //Link recycler view to adapter
    rvMovie.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
    rvMovie.adapter = movieRecyclerViewAdapter

    loadData()
  }

  private fun loadData() {

      val movieService = RetrofitProvider.retrofit().create(MovieService::class.java)

      showLoading()
    movieService.popular("1611e2ffe746c99b86236930d02c2f2e").enqueue(object : Callback<MovieResponse>{
      override fun onFailure(call: Call<MovieResponse>, t: Throwable) {

        t?.printStackTrace()
        showError()
      }

      override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {

        if (response.isSuccessful){
          val movie = response.body()!!
          showMovieList(movie.movieLists)
        }else{
          showError()
        }
      }

    })
     }

  private fun showLoading() {
    progressBar.visibility = View.VISIBLE
    rvMovie.visibility = View.GONE
  }

  private fun showMovieList(movieList: List<Movie>) {
    progressBar.visibility = View.GONE
    rvMovie.visibility = View.VISIBLE
    movieRecyclerViewAdapter.submitList(movieList)
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
