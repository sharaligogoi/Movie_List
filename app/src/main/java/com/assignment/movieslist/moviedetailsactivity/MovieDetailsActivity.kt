package com.assignment.movieslist.moviedetailsactivity

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.assignment.movieslist.R
import com.assignment.movieslist.domain.models.Movie
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class MovieDetailsActivity : AppCompatActivity() {
    private lateinit var ivMoviePoster : ImageView
    private lateinit var tvMovieName: TextView
    private lateinit var cgMovieGenre: ChipGroup
    private lateinit var tvReleaseDate: TextView
    private lateinit var tvReviews: TextView
    private lateinit var tvRating: TextView
    private lateinit var tvMovieSynopsis: TextView
    private lateinit var ratingBar: RatingBar
    private lateinit var tvUserReview: TextView
    private lateinit var ivBack: ImageView

    private val detailsViewModel: MovieDetailsActivityViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        initViews()

        val movie = getMovieIntent()

        tvMovieName.text = movie?.title
        tvReleaseDate.text = "R|3h 7min|${movie?.releaseDate}"
        bindMoviePoster(movie)
        tvMovieSynopsis.text = movie?.description
        tvRating.text = (movie?.ratings?.div(2)).toString()
        ratingBar.rating = ((movie?.ratings)?.div(2)!!)
        tvUserReview.text = movie?.usersReview.toString()+"(Users)"

        Log.d("Genre List", "onCreate: $movie")
        val genreList = movie.genreIds
        Log.d("Genre List", "onCreate: ${movie.genreIds}")
        detailsViewModel.genres.observe(this){
            val map = it

            Log.d("Genre List","$map")
            genreList?.let {
                for(i in 0 until (genreList.size)){
                    val chip = Chip(this)
                    chip.text = map[genreList[i]]?.name

                    cgMovieGenre.addView(chip)
                }
            }

        }
        ivBack.setOnClickListener{
            this.onBackPressed()
        }

    }


    private fun initViews(){
        ivMoviePoster = findViewById(R.id.iv_movie_details_activity_movie_poster)
        tvMovieName = findViewById(R.id.tv_movie_details_activity_movie_name)
        tvReleaseDate = findViewById(R.id.tv_movie_details_activity_release_date)
        cgMovieGenre = findViewById(R.id.cg_movie_details_activity_chip_grp)
        tvReviews = findViewById(R.id.tv_movie_details_activity_reviews)
        tvRating = findViewById(R.id.tv_movie_details_activity_rating)
        tvMovieSynopsis = findViewById(R.id.tv_movie_details_activity_synopsis)
        ratingBar = findViewById(R.id.rtb_movie_details_activity_sore)
        tvUserReview =findViewById(R.id.tv_movie_details_activity_user_review)
        ivBack = findViewById(R.id.iv_movie_details_activity_back)
    }


    private fun getMovieIntent(): Movie? {
        return intent.getParcelableExtra("movie details")

    }

    private fun bindMoviePoster(movie:Movie?){
        val requiresOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)

        Glide.with(this)
            .applyDefaultRequestOptions(requiresOptions)
            .load(movie?.posterUrl)
            .into(ivMoviePoster)
    }






}