package ru.kryu.vkpokemon.feature.pokemonlist.domain.model

data class PokemonPack(
    val count: Int,
    var nextLimit: Int?,
    var nextOffset: Int?,
    val pokemonList: MutableList<PokemonItem>
)
