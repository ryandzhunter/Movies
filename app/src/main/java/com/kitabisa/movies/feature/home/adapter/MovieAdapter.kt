package com.kitabisa.movies.feature.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kitabisa.movies.R
import com.kitabisa.movies.databinding.ItemMovieBinding
import com.kitabisa.movies.model.MovieData

/**
 * Created by Aryandi Putra<aryandi2712@gmail.com> on 14/06/20.
 */
class MovieAdapter(
    private val movies: MutableList<MovieData>,
    private val clickListener: (MovieData) -> Unit
) :
    RecyclerView.Adapter<MovieAdapter.MovieHolder>() {

    companion object {
        const val TMDB_IMAGEURL = "https://image.tmdb.org/t/p/w185/"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieHolder(view)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bind(movies.get(position))
    }

    fun setMovies(movies: List<MovieData>) {
        this.movies.clear()
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }

    inner class MovieHolder(val view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemMovieBinding.bind(view)

        fun bind(movie: MovieData) = with(view) {
            if (movie.posterPath != null) {
                Glide.with(view.context).load(TMDB_IMAGEURL + movie.posterPath)
                    .into(binding.ivMovie)
            }
            binding.tvMovieTitle.text = movie.title
            binding.tvReleaseDate.text = movie.releaseDate
            binding.tvOverview.text = movie.overview
            view.setOnClickListener {
                clickListener(movie)
            }
        }
    }
}