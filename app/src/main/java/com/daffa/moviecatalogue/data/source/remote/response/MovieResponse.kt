package com.daffa.moviecatalogue.data.source.remote.response

import android.os.Parcelable
import com.daffa.moviecatalogue.data.source.remote.response.model.Movie
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieResponse(
    val page: Int,
    val total_results: Int,
    val total_pages: Int,
    val results: List<Movie>
) : Parcelable
