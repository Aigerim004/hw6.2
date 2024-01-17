package com.example.rickandmorty.ui.secondActivity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.rickandmorty.R
import com.example.rickandmorty.data.Resource
import com.example.rickandmorty.data.model.CharacterModel
import com.example.rickandmorty.databinding.ActivitySecondBinding
import com.example.rickandmorty.utils.CharacterKeys
import com.example.rickandmorty.utils.StatusQ
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    private lateinit var viewModel: DetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[DetailsViewModel::class.java]

        val id = intent.getIntExtra(CharacterKeys.CHARACTER_ID_ARG, 0)

        viewModel.getData(id).observe(this) {
            it?.let {
                when (it) {
                    is Resource.Error -> {
                        binding.progressBar.isVisible = false
                        Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    }

                    is Resource.Loading -> {
                        binding.progressBar.isVisible = true
                    }

                    is Resource.Success -> {
                        binding.progressBar.isVisible = false
                        val character = it.data as CharacterModel
                        setCharacterData(character)
                    }
                }
            }
        }

    }

    private fun setCharacterData(it: CharacterModel) = with(binding) {
        Log.e("ololo", "Data is not null")
        tvCharacterName.text = it.name
        tvLocationInfo.text = it.location.toString()
        tvOriginInfo.text = it.origin.toString()
        tvSpecies.text = it.species
        tvStatus.text = it.status
        Glide.with(imageCharacter).load(it.image).into(imageCharacter)

        when (it.status) {
            StatusQ.ALIVE.provider -> imgCircleStatus.setBackgroundResource(R.drawable.circle_green)
            StatusQ.DEAD.provider -> imgCircleStatus.setBackgroundResource(R.drawable.circle_red)
            StatusQ.UNKNOWN.provider -> imgCircleStatus.setBackgroundResource(R.drawable.circle)
        }
    }
}
