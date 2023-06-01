package com.example.laboratorio05.data.model

import androidx.room.Entity

@Entity(tableName = "cast_table", primaryKeys = ["movieID", "actorID"])
data class CastModel(
    val movieID: Int,
    val actorID: Int,
)