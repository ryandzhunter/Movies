package com.kitabisa.movies.feature.detail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kitabisa.movies.R
import com.kitabisa.movies.databinding.ItemReviewBinding
import com.kitabisa.movies.model.Movie
import com.kitabisa.movies.model.Review

/**
 * Created by Aryandi Putra<aryandi2712@gmail.com> on 14/06/20.
 */
class ReviewAdapter(private val reviews : MutableList<Review>) : RecyclerView.Adapter<ReviewAdapter.ReviewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_review, parent, false)
        return ReviewHolder(view)
    }

    override fun getItemCount(): Int = reviews.size

    fun setReviews(movies: List<Review>) {
        this.reviews.clear()
        this.reviews.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ReviewHolder, position: Int) {
        holder.bind(reviews.get(position))
    }

    inner class ReviewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemReviewBinding.bind(view)

        fun bind(review: Review) = with(view) {
            binding.tvAuthor.text = review.author
            binding.tvReview.text = review.content
        }
    }

}
