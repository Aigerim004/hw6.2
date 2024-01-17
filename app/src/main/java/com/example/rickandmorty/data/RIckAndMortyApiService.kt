package com.example.rickandmorty.data

import com.example.rickandmorty.data.model.BaseResponse
import com.example.rickandmorty.data.model.CharacterModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RIckAndMortyApiService {
    @GET("character")
    fun getCharacters(): Call<BaseResponse<CharacterModel>>

    @GET("character/{id}")
    fun getCharacter(@Path("id") id: Int):Call<CharacterModel>
}