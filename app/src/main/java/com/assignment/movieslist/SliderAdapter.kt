package com.assignment.movieslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.assignment.movieslist.domain.models.Movie
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class SliderAdapter(
    val viewPager: ViewPager2,
    private val movieList: List<Movie>
) : RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_first_list_movie_model, parent, false)

        return SliderViewHolder(view)
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.onBind(movieList[position])
    }

    override fun getItemCount(): Int = movieList.size

    class SliderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val img: ImageView = itemView.findViewById(R.id.iv_first_list_movie_model)

        fun onBind(movie: Movie?){
            val requestOptions = RequestOptions
                .placeholderOf(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView)
                .applyDefaultRequestOptions(requestOptions)
                .load(movie?.posterUrl)
                .into(img)
        }
    }

}