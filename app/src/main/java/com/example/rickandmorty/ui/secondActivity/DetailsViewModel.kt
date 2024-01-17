package com.example.rickandmorty.ui.secondActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.data.Repository
import com.example.rickandmorty.data.Resource
import com.example.rickandmorty.data.model.CharacterModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    fun getData(id: Int): LiveData<Resource<CharacterModel>> = repository.getCharacter(id)
}