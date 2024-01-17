package com.example.rickandmorty.ui.character

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmorty.R
import com.example.rickandmorty.data.model.CharacterModel
import com.example.rickandmorty.databinding.ItemListBinding
import com.example.rickandmorty.utils.StatusQ

class CharacterAdapter(
    private val onClick: (character: CharacterModel) -> Unit,
) : ListAdapter<CharacterModel, CharacterViewHolder>(
    CharacterDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CharacterViewHolder(
        ItemListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ), onClick

    )

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class CharacterViewHolder(
    private val binding: ItemListBinding,
    private val onClick: (character: CharacterModel) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("ResourceAsColor")
    fun bind(character: CharacterModel) {
        binding.run {
            Glide.with(imageCharacter).load(character.image)
                .into(imageCharacter)
            tvCharacterName.text = character.name
            tvStatus.text = character.status
            tvSpecies.text = character.species
            tvLocationInfo.text = character.location.name
            itemView.setOnClickListener {
                onClick(character)
            }

            when (character.status) {
                StatusQ.ALIVE.provider -> imgCircleStatus.setBackgroundResource(R.drawable.circle_green)
                StatusQ.DEAD.provider -> imgCircleStatus.setBackgroundResource(R.drawable.circle_red)
                StatusQ.UNKNOWN.provider -> imgCircleStatus.setBackgroundResource(R.drawable.circle)
            }
        }
    }
}

class CharacterDiffCallback : DiffUtil.ItemCallback<CharacterModel>() {
    override fun areItemsTheSame(oldItem: CharacterModel, newItem: CharacterModel) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: CharacterModel, newItem: CharacterModel) = oldItem == newItem

}