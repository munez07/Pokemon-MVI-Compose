package com.example.pokemon.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKey(
    @PrimaryKey
    val pokemonId: Int,
    val prevKey: Int?,
    val nextKey: Int?
)
