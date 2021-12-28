package com.assignment.movieslist.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.assignment.movieslist.R
import com.assignment.movieslist.SliderAdapter
import com.assignment.movieslist.databinding.FragmentHomeBinding
import com.assignment.movieslist.domain.models.Movie
import com.assignment.movieslist.moviedetailsactivity.MovieDetailsActivity
import com.assignment.movieslist.ui.adapters.MoviesListAdapter
import kotlin.math.abs

class HomeFragment : Fragment(), MoviesListAdapter.OnMovieClickListener {

    private val homeViewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null

    private lateinit var firstMovieListAdapter: SliderAdapter
    private lateinit var secondMovieListAdapter: MoviesListAdapter
    private lateinit var vpFirstMovieList: ViewPager2
    private lateinit var rcvSecondMovieList: RecyclerView

    private lateinit var gridLayoutManager: GridLayoutManager


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_home, container, false)

        setUpRecyclerView(root)

        homeViewModel.listUpdateEvent.observe(viewLifecycleOwner) {
            secondMovieListAdapter.notifyItemRangeInserted(it.first, it.last)
            firstMovieListAdapter.notifyItemRangeInserted(it.first,it.last)
        }


        setViewPager()

        secondMovieListAdapter = MoviesListAdapter(homeViewModel.movieList, this)
        rcvSecondMovieList.adapter = secondMovieListAdapter

        rcvSecondMovieList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (gridLayoutManager.findLastVisibleItemPosition() >= secondMovieListAdapter.itemCount - 7) {
                    homeViewModel.loadMovies()
                }
            }
        })


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun setUpRecyclerView(view: View) {
        vpFirstMovieList = view.findViewById(R.id.vp_home_fragment_movies)
        rcvSecondMovieList = view.findViewById(R.id.rcv_home_fragment_second_movie_list)


        gridLayoutManager = GridLayoutManager(context, 3)
        rcvSecondMovieList.layoutManager = gridLayoutManager

    }


    private fun getFirstMovieList(): ArrayList<Movie> {
        val list = ArrayList<Movie>()

        for (i in 0..4) {
            list.add(homeViewModel.movieList[i])
        }

        return list
    }

    private fun setViewPager(){
        firstMovieListAdapter = SliderAdapter(vpFirstMovieList,homeViewModel.movieList)
        vpFirstMovieList.adapter = firstMovieListAdapter
        vpFirstMovieList.clipToPadding = false
        vpFirstMovieList.clipChildren = false
        vpFirstMovieList.offscreenPageLimit = 3
        vpFirstMovieList.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        compositePageTransformer.addTransformer { page, position ->
            val r: Float = 1- abs(position)
            page.scaleY = 0.85f + r*0.15f
        }

        vpFirstMovieList.setPageTransformer(compositePageTransformer)


    }

    override fun onClick(position: Int) {
        Toast.makeText( context,"item clicked", Toast.LENGTH_SHORT).show()
        val movieDetailsIntent = Intent(context, MovieDetailsActivity::class.java)
        movieDetailsIntent.putExtra("movie details", homeViewModel.movieList[position])
        startActivity(movieDetailsIntent)
    }
}