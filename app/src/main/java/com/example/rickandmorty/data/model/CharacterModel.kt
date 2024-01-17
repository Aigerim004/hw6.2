package com.example.rickandmorty.data.model

import java.io.Serializable



data class BaseResponse<T>(
    var results: List<T>
) : java.io.Serializable

data class CharacterModel(
    var id: Int,
    var name: String,
    var status: String,
    var image: String,
    var species: String,
    var origin: Origin,
    var location: Location,
) : java.io.Serializable

data class Origin(
    var name: String
) : java.io.Serializable

data class Location(
    var name: String
) : Serializable
