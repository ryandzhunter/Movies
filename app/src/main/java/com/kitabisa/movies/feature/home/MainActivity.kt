package com.kitabisa.movies.feature.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.kitabisa.movies.databinding.ActivityMainBinding
import com.kitabisa.movies.feature.home.adapter.MovieAdapter
import com.kitabisa.movies.model.MovieData
import com.kitabisa.movies.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModel()
    private val adapter = MovieAdapter(mutableListOf()) { doOnClickAdapter(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvMovies.layoutManager = LinearLayoutManager(this)
        binding.rvMovies.adapter = adapter

        mainViewModel.getMovies()
        mainViewModel.moviesLiveData.observe(this, Observer<List<MovieData>> {
            adapter.setMovies(it)
        })
    }

    private fun doOnClickAdapter(movie: MovieData) {

    }

}
