package com.kitabisa.movies.feature.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.kitabisa.movies.databinding.ActivityMovieDetailBinding
import com.kitabisa.movies.feature.detail.adapter.ReviewAdapter
import com.kitabisa.movies.feature.home.adapter.MovieAdapter
import com.kitabisa.movies.model.MovieDetail
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
        viewModel.moviesLiveData.observe(this, Observer<MovieDetail> {
            showDetailMovie(it)
        })
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

    private fun showDetailMovie(it: MovieDetail) {
        Glide.with(this).load(MovieAdapter.TMDB_IMAGEURL + it.posterPath)
            .into(binding.ivMovie)
        binding.tvMovieTitle.text = it.originalTitle
        binding.tvReleaseDate.text = it.releaseDate
        binding.tvOverview.text = it.overview
        it.reviews?.results?.let { reviews -> adapter.setReviews(reviews) }
    }
}