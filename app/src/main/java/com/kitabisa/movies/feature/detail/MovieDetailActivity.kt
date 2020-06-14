package com.kitabisa.movies.feature.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.kitabisa.movies.R
import com.kitabisa.movies.databinding.ActivityMovieDetailBinding
import com.kitabisa.movies.feature.detail.adapter.ReviewAdapter
import com.kitabisa.movies.feature.home.adapter.MovieAdapter
import com.kitabisa.movies.model.MovieDetail
import com.kitabisa.movies.model.toMovie
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Aryandi Putra<aryandi2712@gmail.com> on 14/06/20.
 */
class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMovieDetailBinding
    private val viewModel: MovieDetailViewModel by viewModel()
    private val adapter = ReviewAdapter(mutableListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()

        val id:Int = intent.getIntExtra("id", -1)
        viewModel.getMovieDetail(id)
        viewModel.isMovieFavorite(id)
        viewModel.moviesLiveData.observe(this, Observer<MovieDetail> {
            showDetailMovie(it)
        })
        viewModel.favoriteState.observe(this, Observer<Boolean> { isFavorite ->
            setFavoriteIcon(isFavorite)
        })
    }

    private fun setFavoriteIcon(isFavorite: Boolean) {
        if (isFavorite) {
            binding.bFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite_active
                )
            )
        } else {
            binding.bFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite_inactive
                )
            )
        }
    }

    private fun initRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(this)
        binding.rvReview.layoutManager = linearLayoutManager
        val dividerItemDecoration = DividerItemDecoration(
            binding.rvReview.getContext(),
            linearLayoutManager.getOrientation()
        )
        binding.rvReview.addItemDecoration(dividerItemDecoration)
        binding.rvReview.adapter = adapter
    }

    private fun showDetailMovie(movieDetail: MovieDetail) {
        Glide.with(this).load(MovieAdapter.TMDB_IMAGEURL + movieDetail.posterPath)
            .into(binding.ivMovie)
        binding.tvMovieTitle.text = movieDetail.originalTitle
        binding.tvReleaseDate.text = movieDetail.releaseDate
        binding.tvOverview.text = movieDetail.overview
        movieDetail.reviews?.results?.let { reviews -> adapter.setReviews(reviews) }
        binding.bFavorite.setOnClickListener {
            viewModel.onFavoriteButtonClicked(toMovie(movieDetail))
        }
    }
}