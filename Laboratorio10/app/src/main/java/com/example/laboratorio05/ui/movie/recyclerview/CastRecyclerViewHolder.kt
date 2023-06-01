package com.example.laboratorio05.ui.movie.recyclerview

import androidx.recyclerview.widget.RecyclerView
import com.example.laboratorio05.data.model.ActorModel
import com.example.laboratorio05.databinding.ActorItemBinding

class CastRecyclerViewHolder(private val binding: ActorItemBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(actor: ActorModel) {
        binding.actorNameTextView.text = actor.name
    }
}