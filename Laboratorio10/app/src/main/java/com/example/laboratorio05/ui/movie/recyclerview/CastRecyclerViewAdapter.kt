package com.example.laboratorio05.ui.movie.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.laboratorio05.data.model.ActorModel
import com.example.laboratorio05.databinding.ActorItemBinding

class CastRecyclerViewAdapter: RecyclerView.Adapter<CastRecyclerViewHolder>() {

   private val cast = ArrayList<ActorModel>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastRecyclerViewHolder {
        val binding = ActorItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CastRecyclerViewHolder(binding)
    }

    override fun getItemCount() = cast.size

    override fun onBindViewHolder(holder: CastRecyclerViewHolder, position: Int) {
        val actor = cast[position]
        holder.bind(actor)
    }

    fun setData(castList: List<ActorModel>) {
        cast.clear()
        cast.addAll(castList)
    }
}