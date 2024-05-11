package ru.kryu.vkpokemon.feature.pokemonlist.presentation.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.kryu.vkpokemon.databinding.PokemonListItemBinding
import ru.kryu.vkpokemon.feature.pokemonlist.domain.model.PokemonItem

class PokemonAdapter(private val clickListener: PokemonItemClickListener) :
    RecyclerView.Adapter<PokemonViewHolder>() {

    private val pokemonList: MutableList<PokemonItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PokemonViewHolder(
            PokemonListItemBinding.inflate(inflater, parent, false),
            clickListener
        )
    }

    override fun getItemCount(): Int = pokemonList.size

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(pokemonList[position])
    }

    fun addPokemonList(newPokemonList: List<PokemonItem>) {
        val diffCallback = PokemonDiffCallback(pokemonList, newPokemonList)
        val diffPokemon = DiffUtil.calculateDiff(diffCallback)
        pokemonList.clear()
        pokemonList.addAll(newPokemonList)
        diffPokemon.dispatchUpdatesTo(this)
    }

    fun interface PokemonItemClickListener {
        fun onClick(pokemonItem: PokemonItem)
    }
}
