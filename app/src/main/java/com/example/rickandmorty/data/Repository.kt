package com.example.rickandmorty.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.load.engine.Resource
import com.example.rickandmorty.data.model.BaseResponse
import com.example.rickandmorty.data.model.CharacterModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(private val api: RIckAndMortyApiService) {
    fun getCharacters(): MutableLiveData<Resource<List<CharacterModel>>> {
        val characters = MutableLiveData<Resource<List<CharacterModel>>>()
        characters.postValue(Resource.Loading())
        api.getCharacters().enqueue(object : Callback<BaseResponse<CharacterModel>> {
            override fun onResponse(
                call: Call<BaseResponse<CharacterModel>>,
                response: Response<BaseResponse<CharacterModel>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    response.body()?.let {
                        characters.postValue(Resource.Success(it.results))
                    }
                }
            }

            override fun onFailure(call: Call<BaseResponse<CharacterModel>>, t: Throwable) {
                characters.postValue(Resource.Error(t.localizedMessage?: "Unknowm error"))
                Log.e("edu", t.message.toString())
            }

        })
        return characters
    }

    fun getCharacter(id: Int): LiveData<Resource<CharacterModel>> {
        val characterLv = MutableLiveData<Resource<CharacterModel>>()
        characterLv.postValue(Resource.Loading())
        api.getCharacter(id).enqueue(object : Callback<CharacterModel> {
            override fun onResponse(
                call: Call<CharacterModel>,
                response: Response<CharacterModel>
            ) {
                response?.body().let {
                    characterLv.postValue(Resource.Success(response.body()!!))
                }
            }

            override fun onFailure(call: Call<CharacterModel>, t: Throwable) {
                characterLv.postValue(Resource.Error(t.localizedMessage?: "Unknowm error"))
                Log.e("edu", t.message.toString())
            }
        })
        return characterLv
    }
}