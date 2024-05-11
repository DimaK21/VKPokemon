package ru.kryu.vkpokemon.feature.pokemoninfo.presentation

import ru.kryu.vkpokemon.feature.pokemoninfo.domain.model.Pokemon

sealed class PokemonInfoState {
    data object Loading : PokemonInfoState()
    data object Error : PokemonInfoState()
    data class Content(val content: Pokemon) : PokemonInfoState()
}