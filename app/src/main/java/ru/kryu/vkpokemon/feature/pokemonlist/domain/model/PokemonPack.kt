package ru.kryu.vkpokemon.feature.pokemonlist.domain.model

data class PokemonPack(
    val count: Int,
    var next: String?,
    val pokemonList: MutableList<PokemonItem>
)
