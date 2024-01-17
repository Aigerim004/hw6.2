package com.example.rickandmorty.ui.mainActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmorty.ui.secondActivity.SecondActivity
import com.example.rickandmorty.utils.CharacterKeys
import com.example.rickandmorty.R
import com.example.rickandmorty.data.Resource
import com.example.rickandmorty.data.model.CharacterModel
import com.example.rickandmorty.databinding.ActivityMainBinding
import com.example.rickandmorty.ui.character.CharacterAdapter
import com.example.rickandmorty.ui.character.CharacterViewModel

import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: CharacterViewModel
    private val characterAdapter by lazy { CharacterAdapter(this::onClickItem) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[CharacterViewModel::class.java]

        viewModel.getCharacters().observe(this) {
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
                    characterAdapter.submitList(it.data)
                }
            }
        }
        setupCharactersRecycler()
    }

    private fun setupCharactersRecycler() /*= with(binding.rv)*/ {
        binding.rv.layoutManager = LinearLayoutManager(
            this@MainActivity,
            LinearLayoutManager.VERTICAL,
            false
        )
        binding.rv.adapter = characterAdapter
    }

    private fun onClickItem(character: CharacterModel) {
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra(CharacterKeys.CHARACTER_ID_ARG, character.id)
        startActivity(intent)
    }

}