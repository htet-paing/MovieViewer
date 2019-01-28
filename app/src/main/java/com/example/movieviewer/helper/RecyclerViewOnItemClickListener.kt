package com.example.movieviewer.helper

import android.view.View

/**
 * Created by Vincent on 11/10/18
 */
public interface RecyclerViewOnItemClickListener {

  fun onItemClick(clickedView: View, position: Int)
}