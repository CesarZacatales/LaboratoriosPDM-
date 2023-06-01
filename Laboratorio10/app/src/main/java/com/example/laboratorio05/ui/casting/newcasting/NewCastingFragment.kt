package com.example.laboratorio05.ui.casting.newcasting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.laboratorio05.R
import com.example.laboratorio05.data.model.ActorModel
import com.example.laboratorio05.data.model.MovieModel
import com.example.laboratorio05.databinding.FragmentNewCastingBinding
import com.example.laboratorio05.ui.actor.viewmodel.ActorViewModel
import com.example.laboratorio05.ui.casting.viewmodel.CastingViewModel
import com.example.laboratorio05.ui.movie.viewmodel.MovieViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewCastingFragment : Fragment() {

    private val castingViewModel: CastingViewModel by activityViewModels {
        CastingViewModel.Factory
    }

    private val moviesViewModel: MovieViewModel by activityViewModels {
        MovieViewModel.Factory
    }

    private val actorsViewModel: ActorViewModel by activityViewModels {
        ActorViewModel.Factory
    }

    private lateinit var binding: FragmentNewCastingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewCastingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setViewModel()
        observeStatus()

        CoroutineScope(Dispatchers.Main).launch {
            getMoviesAndLaunchSpinner()
            getActorsAndLaunchSpinner()
        }
    }

    private fun setViewModel() {
        binding.viewmodel = castingViewModel
    }

    private suspend fun getMoviesAndLaunchSpinner() {

         setMoviesSpinner(moviesViewModel.getMovies())
    }

    private fun setMoviesSpinner(moviesList: List<MovieModel>) {
        val moviesAdapter = ArrayAdapter(requireContext(), R.layout.spinner_item, moviesList.map { it.name })
        binding.moviesSpinner.adapter = moviesAdapter

        binding.moviesSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // Gets the selected element of the Spinner
                val selectedMovie = moviesList[position]
                castingViewModel.selectMovie(selectedMovie)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handles the case where no element is selected
            }
        }
    }

    private suspend fun getActorsAndLaunchSpinner() {
        setActorsSpinner(actorsViewModel.getAllActors())
    }

    private fun setActorsSpinner(actorList: List<ActorModel>) {
        val actorsAdapter = ArrayAdapter(requireContext(), R.layout.spinner_item, actorList.map { it.name })
        binding.actorsSpinner.adapter = actorsAdapter

        binding.actorsSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // Gets the selected element of the Spinner
                val selectedActor = actorList[position]
                castingViewModel.selectActor(selectedActor)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handles the case where no element is selected
            }
        }
    }

    private fun observeStatus() {
        castingViewModel.status.observe(viewLifecycleOwner) { status ->
            when {
                status.equals(CastingViewModel.WRONG_INFORMATION) -> {
                    castingViewModel.clearStatus()
                }
                status.equals(CastingViewModel.CAST_CREATED) -> {
                    castingViewModel.clearStatus()
                    findNavController().popBackStack()
                }
            }
        }
    }

}