package ru.kryu.vkpokemon.feature.pokemonlist.presentation

import androidx.recyclerview.widget.DiffUtil
import ru.kryu.vkpokemon.feature.pokemonlist.domain.model.PokemonItem

class PokemonDiffCallback(
    private val oldList: List<PokemonItem>,
    private val newList: List<PokemonItem>,
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }
}