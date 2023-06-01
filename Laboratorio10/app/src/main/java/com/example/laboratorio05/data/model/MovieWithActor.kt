package com.example.laboratorio05.data.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class MovieWithActor(
    @Embedded val movie: MovieModel,

    @Relation(
        parentColumn = "movieID",
        entityColumn = "actorID",
        associateBy = Junction(CastModel::class)
    )

    val actors: List<ActorModel>
)