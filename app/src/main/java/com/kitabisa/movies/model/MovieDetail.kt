package com.kitabisa.movies.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Aryandi Putra<aryandi2712@gmail.com> on 14/06/20.
 */
data class MovieDetail(

    @SerializedName("adult")
    var adult: Boolean = false,

    @SerializedName("budget")
    var budget: Int? = null,

    @SerializedName("genres")
    var genres: List<Genre>? = null,

    @SerializedName("reviews")
    var reviews: ReviewsResult? = null,

    @SerializedName("homepage")
    var homepage: String? = null,

    @SerializedName("id")
    var id: Int = -1,

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