package ru.kryu.vkpokemon.feature.pokemonlist.presentation

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import ru.kryu.vkpokemon.R
import ru.kryu.vkpokemon.databinding.PokemonListItemBinding
import ru.kryu.vkpokemon.feature.pokemonlist.domain.model.PokemonItem

class PokemonViewHolder(
    private val binding: PokemonListItemBinding,
    private val clickListener: PokemonAdapter.PokemonItemClickListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(pokemonItem: PokemonItem) {
        binding.tvNamePokemon.text = pokemonItem.name
        Glide.with(itemView)
            .load(pokemonItem.icon)
            .placeholder(R.drawable.pokemon_placeholder)
            .transform(
                CenterCrop(),
                RoundedCorners(itemView.resources.getDimensionPixelSize(R.dimen.icon_corner))
            )
            .into(binding.ivIconPokemon)
        itemView.setOnClickListener { clickListener.onClick(pokemonItem) }
    }
}
