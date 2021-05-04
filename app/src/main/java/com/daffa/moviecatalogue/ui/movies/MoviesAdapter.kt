package com.daffa.moviecatalogue.ui.movies

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.daffa.moviecatalogue.R
import com.daffa.moviecatalogue.data.source.remote.response.model.Movie
import com.daffa.moviecatalogue.databinding.ItemsMoviesBinding
import com.daffa.moviecatalogue.ui.detail.DetailFilmActivity
import com.daffa.moviecatalogue.utils.Constants
import com.daffa.moviecatalogue.utils.Constants.API_POSTER_PATH

class MoviesAdapter() : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    var data: List<Movie> = arrayListOf()

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(id: String)
    }

    fun setMovies(movies: List<Movie>) {
        this.data = movies
        notifyDataSetChanged()
    }

    inner class MoviesViewHolder(private val binding: ItemsMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(API_POSTER_PATH + movie.poster_path)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(imgPoster)
                tvMovieTitle.text = movie.title
                tvMovieReleaseDate.text = movie.release_date
            }

            itemView.setOnClickListener { onItemClickCallback.onItemClicked(movie.id.toString())  }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviesAdapter.MoviesViewHolder {
        val itemsMoviesBinding =
            ItemsMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(itemsMoviesBinding)
    }

    override fun onBindViewHolder(holder: MoviesAdapter.MoviesViewHolder, position: Int) {
        val movies = data[position]
        holder.bind(movies)
    }

    override fun getItemCount(): Int = data.size
}