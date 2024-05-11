package ru.kryu.vkpokemon.feature.pokemonlist.presentation

import ru.kryu.vkpokemon.feature.pokemonlist.domain.model.PokemonPack

sealed class PokemonListState {
    data object Loading : PokemonListState()
    data object Error : PokemonListState()
    data class Content(val content: PokemonPack, var isBottomLoading: Boolean = false) :
        PokemonListState()
}
