package com.kitabisa.movies.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.kitabisa.movies.database.DB_FAVORITE_MOVIE_TABLE_NAME

/**
 * Created by Aryandi Putra<aryandi2712@gmail.com> on 14/06/20.
 */
data class MovieDetail(

    @SerializedName("id")
    @PrimaryKey
    var id: Int = -1,

    @SerializedName("adult")
    var adult: Boolean = false,

    @SerializedName("budget")
    var budget: Int? = null,

    var genres: List<Genre>? = null,

    @SerializedName("reviews")
    var reviews: ReviewsResult? = null,

    @SerializedName("homepage")
    var homepage: String? = null,

    @SerializedName("imdb_id")
    var imdbId: String? = null,

    @SerializedName("popularity")
    var popularity: Float = 0F,

    @SerializedName("revenue")
    var revenue: Int? = null,

    @SerializedName("runtime")
    var runtime: Int? = null,

    @SerializedName("tagline")
    var tagline: String? = null,

    @SerializedName("video")
    var video: Boolean = false,

    @SerializedName("vote_average")
    var voteAverage: Float = 0F,

    @SerializedName("vote_count")
    var voteCount: Int = 0,

    @SerializedName("title")
    var title: String,

    @SerializedName("poster_path")
    var posterPath: String,

    @SerializedName("original_language")
    var originalLanguage: String,

    @SerializedName("original_title")
    var originalTitle: String,

    @SerializedName("backdrop_path")
    var backdropPath: String,

    @SerializedName("overview")
    var overview: String,

    @SerializedName("release_date")
    var releaseDate: String
)

data class Genre(
    var id: Int,
    var name: String
)

class ReviewsResult {
    var results: List<Review>? = null
}

data class Review(
    var id: String,
    var author: String,
    var content: String? = null
)

fun toMovie(movieDetail: MovieDetail): Movie {
    movieDetail.let {
        val genresId = arrayListOf<Int>()
        it.genres?.forEach {
            genresId.add(it.id)
        }
        return Movie(
            it.id,
            it.voteCount,
            it.video,
            it.voteAverage,
            it.title,
            it.popularity,
            it.posterPath,
            it.originalLanguage,
            it.originalTitle,
            genresId,
            it.backdropPath,
            it.adult,
            it.overview
        )
    }
}
