package com.kitabisa.movies.feature.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.kitabisa.movies.R
import com.kitabisa.movies.databinding.ActivityMainBinding
import com.kitabisa.movies.feature.detail.MovieDetailActivity
import com.kitabisa.movies.feature.home.adapter.MovieAdapter
import com.kitabisa.movies.model.Movie
import com.kitabisa.movies.feature.home.viewmodel.MainViewModel
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
        binding.bCategories.setOnClickListener {
            showPopUpMenu()
        }

        mainViewModel.getMovies()
        mainViewModel.moviesLiveData.observe(this, Observer<List<Movie>> {
            adapter.setMovies(it)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()
        if (id == R.id.action_favorite) {
            mainViewModel.getFavoriteMovies()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showPopUpMenu() {
        val popupMenu = PopupMenu(this, binding.bCategories)
        popupMenu.menuInflater.inflate(R.menu.popup_menu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { item: MenuItem? ->
            when (item!!.itemId) {
                R.id.popular -> {
                    mainViewModel.getPopularMovies()
                }
                R.id.upcoming -> {
                    mainViewModel.getUpcomingMovies()
                }
                R.id.toprated -> {
                    mainViewModel.getTopRatedMovies()
                }
                R.id.nowplaying -> {
                    mainViewModel.getNowPlayingMovies()
                }
            }
            true
        }
        popupMenu.show()
    }

    private fun doOnClickAdapter(movie: Movie) {
        movie.id?.let {
            val intent = Intent(this, MovieDetailActivity::class.java)
            intent.putExtra("id", it)
            startActivity(intent)
        }
    }

}
