package com.example.rickandmorty.ui.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.data.Repository
import com.example.rickandmorty.data.Resource
import com.example.rickandmorty.data.model.CharacterModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    fun getCharacters(): LiveData<Resource<List<CharacterModel>>> = repository.getCharacters()
}