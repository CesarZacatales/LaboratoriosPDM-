package com.example.laboratorio05.ui.movie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.laboratorio05.databinding.FragmentMovieBinding
import com.example.laboratorio05.ui.movie.recyclerview.CastRecyclerViewAdapter
import com.example.laboratorio05.ui.movie.viewmodel.MovieViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MovieFragment : Fragment() {

    private val movieViewModel: MovieViewModel by activityViewModels {
        MovieViewModel.Factory
    }

    private lateinit var binding: FragmentMovieBinding

    private lateinit var adapter: CastRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CoroutineScope(lifecycleScope.coroutineContext).launch {
            setRecyclerView(view)
        }

        setViewModel()

    }

    private fun setRecyclerView(view: View) {
        binding.actorsRecyclerView.layoutManager = LinearLayoutManager(view.context)

        adapter = CastRecyclerViewAdapter()

        binding.actorsRecyclerView.adapter = adapter
        displayCast()
    }

    private fun displayCast() {
        val movieId = movieViewModel.id
        CoroutineScope(lifecycleScope.coroutineContext).launch {
            val movieWithActor = movieViewModel.getMovieWithActorsById(movieId.value!!)

            val actors = movieWithActor?.actors
            adapter.setData(actors!!)

            adapter.notifyDataSetChanged()
        }
    }

    private fun setViewModel() {
        binding.viewmodel = movieViewModel
    }

}