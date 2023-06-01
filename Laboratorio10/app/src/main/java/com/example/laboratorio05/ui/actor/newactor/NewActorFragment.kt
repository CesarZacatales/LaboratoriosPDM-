package com.example.laboratorio05.ui.actor.newactor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.laboratorio05.databinding.FragmentNewActorBinding
import com.example.laboratorio05.ui.actor.viewmodel.ActorViewModel

class NewActorFragment : Fragment() {

    private val actorViewModel: ActorViewModel by activityViewModels {
        ActorViewModel.Factory
    }

    private lateinit var binding: FragmentNewActorBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewActorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewModel()
        observeStatus()
    }

    private fun setViewModel() {
        binding.viewmodel = actorViewModel
    }

    private fun observeStatus() {
        actorViewModel.status.observe(viewLifecycleOwner) { status ->
            when {
                status.equals(ActorViewModel.WRONG_INFORMATION) -> {
                    actorViewModel.clearStatus()
                }
                status.equals(ActorViewModel.ACTOR_CREATED) -> {
                    actorViewModel.clearStatus()
                    findNavController().popBackStack()
                }
            }
        }
    }

}