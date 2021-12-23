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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.assignment.movieslist.R
import com.assignment.movieslist.databinding.FragmentHomeBinding
import com.assignment.movieslist.domain.models.Movie
import com.assignment.movieslist.moviedetailsactivity.MovieDetailsActivity
import com.assignment.movieslist.ui.adapters.MoviesListAdapter

class HomeFragment : Fragment(), MoviesListAdapter.OnMovieClickListener {

    private val homeViewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null

    private lateinit var firstMovieListAdapter: MoviesListAdapter
    private lateinit var secondMovieListAdapter: MoviesListAdapter
    private lateinit var rcvFirstMovieList: RecyclerView
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

        firstMovieListAdapter = MoviesListAdapter(homeViewModel.movieList, this)
        rcvFirstMovieList.adapter = firstMovieListAdapter

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
        rcvFirstMovieList = view.findViewById(R.id.rcv_home_fragment_first_movies_list)
        rcvSecondMovieList = view.findViewById(R.id.rcv_home_fragment_second_movie_list)

        rcvFirstMovieList.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )

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

    override fun onClick(position: Int) {
        Toast.makeText( context,"item clicked", Toast.LENGTH_SHORT).show()
        val movieDetailsIntent = Intent(context, MovieDetailsActivity::class.java)
        movieDetailsIntent.putExtra("movie details", homeViewModel.movieList[position])
        startActivity(movieDetailsIntent)
    }
}