  package com.example.movieviewer.feature.browse

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.movieviewer.R
import com.example.movieviewer.feature.browse.MovieRecyclerViewAdapter.MovieViewHolder
import com.example.movieviewer.helper.GlideApp
import com.example.movieviewer.helper.RecyclerViewOnItemClickListener
import com.example.movieviewer.model.Movie
import kotlinx.android.synthetic.main.item_browse.view.ivPoster

/**
 * Created by Vincent on 11/10/18
 */
class MovieRecyclerViewAdapter : RecyclerView.Adapter<MovieViewHolder>() {

  private val movieList = mutableListOf<Movie>()
  private var recyclerViewOnItemClickListener: RecyclerViewOnItemClickListener? = null

  //region List related methods
  fun submitList(movieList: List<Movie>) {
    this.movieList.clear()
    this.movieList.addAll(movieList)
    notifyDataSetChanged()

  }

  fun getItem(position: Int): Movie {
    return movieList[position]
  }
  //endregion

  //region Setter for onItemClickListener
  fun setRecyclerViewOnItemClickListener(recyclerViewOnItemClickListener: RecyclerViewOnItemClickListener) {
    this.recyclerViewOnItemClickListener = recyclerViewOnItemClickListener;
    notifyDataSetChanged()
  }

  //endregion

  //region RecyclerView Adapter

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
    val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_browse, parent, false)
    return MovieViewHolder(itemView, recyclerViewOnItemClickListener)
  }

  override fun getItemCount(): Int = movieList.size

  override fun onBindViewHolder(viewHolder: MovieViewHolder, position: Int) {
    viewHolder.bindMovie(getItem(position))
  }

  //endregion

  //region ViewHolder

  inner class MovieViewHolder(
    itemView: View,
    recyclerViewOnItemClickListener: RecyclerViewOnItemClickListener?
  ) : RecyclerView.ViewHolder(itemView) {

    private val ivPoster = itemView.ivPoster

    init {
      itemView.setOnClickListener {
        recyclerViewOnItemClickListener?.onItemClick(it, adapterPosition)
      }
    }

    fun bindMovie(item: Movie) {
      //TODO("Show Image")
      val fullPosterPath = "https://image.tmdb.org/t/p/w500/${item.posterPath}"
      GlideApp.with(itemView.context)
        .load(fullPosterPath)
        .centerCrop()
        .placeholder(R.drawable.placeholder_bg)
        .into(ivPoster)

    }

  }

  //endregion
}