package com.example.laboratorio05zac.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.laboratorio05zac.R
import com.example.laboratorio05zac.databinding.FragmentDetailsMovieBinding

class detailsMovieFragment : Fragment() {

     private lateinit var binding: FragmentDetailsMovieBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsMovieBinding.inflate(inflater, container, false)
        return binding.root
    }
 }

