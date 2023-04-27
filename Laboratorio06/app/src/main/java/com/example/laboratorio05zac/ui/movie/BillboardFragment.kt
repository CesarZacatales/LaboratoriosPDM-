package com.example.laboratorio05zac.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import com.example.laboratorio05zac.R
import com.google.android.material.floatingactionbutton.FloatingActionButton


class billboardFragment : Fragment() {

    private lateinit var buttonNewMovie: FloatingActionButton
    private lateinit var detailsMovie: CardView
    private lateinit var detailsMovieTwo: CardView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_billboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        bind()
        buttonNewMovie.setOnClickListener {
            it.findNavController().navigate(R.id.action_billboardFragment_to_newMovieFragment)
        }
        detailsMovie.setOnClickListener {
            it.findNavController().navigate(R.id.action_billboardFragment_to_detailsMovieFragment)
        }
    }

    private fun bind(){
        buttonNewMovie = view?.findViewById(R.id.action_to_create_movie) as FloatingActionButton
        detailsMovie = view?.findViewById(R.id.firstCard) as CardView
        detailsMovieTwo = view?.findViewById(R.id.SecondCard) as CardView
    }
}