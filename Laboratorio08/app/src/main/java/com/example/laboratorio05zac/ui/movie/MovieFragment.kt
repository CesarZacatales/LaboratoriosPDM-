package com.example.laboratorio05zac.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.laboratorio05zac.data.movies
import com.example.laboratorio05zac.data.name
import com.example.laboratorio05zac.databinding.FragmentMovieBinding
import com.example.laboratorio05zac.ui.movie.billboard.recyclerview.MovieRecyclerViewAdapter
import com.example.laboratorio05zac.ui.movie.viewmodel.MovieViewModel

class MovieFragment : Fragment() {

    private val movieViewModel: MovieViewModel by activityViewModels {
        MovieViewModel.Factory
    }
     private lateinit var binding: FragmentMovieBinding

     private lateinit var adapter: MovieRecyclerViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }
    private fun setViewModel(){
        binding.textView
        binding.viewmodel = movieViewModel
    }

}

