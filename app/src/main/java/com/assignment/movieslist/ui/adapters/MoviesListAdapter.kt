package com.assignment.movieslist.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.assignment.movieslist.R
import com.assignment.movieslist.domain.models.Movie
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class MoviesListAdapter(
    private val movieList: List<Movie>,
    private val onMovieClickListener: OnMovieClickListener
) : RecyclerView.Adapter<MoviesListAdapter.MovieModelViewHolder>() {

    override fun getItemCount(): Int = movieList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieModelViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_now_showing_movie_model, parent, false)


        return MovieModelViewHolder(view, onMovieClickListener)
    }

    override fun onBindViewHolder(holder: MovieModelViewHolder, position: Int) {
        holder.onBind(movieList[position])
    }


    class MovieModelViewHolder(
        itemView: View,
        private val onMovieClickListener: OnMovieClickListener
    ) :
        RecyclerView.ViewHolder(itemView) {
        private val ivMovieBanner: ImageView = itemView.findViewById(R.id.iv_now_showing_movie_list)

        init {
            itemView.setOnClickListener{
                onMovieClickListener.onClick(adapterPosition)
            }
        }

        fun onBind(movie: Movie) {
            val requestOptions = RequestOptions
                .placeholderOf(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView)
                .applyDefaultRequestOptions(requestOptions)
                .load(movie.posterUrl)
                .into(ivMovieBanner)
        }


    }

    interface OnMovieClickListener {
        fun onClick(position: Int)
    }
}