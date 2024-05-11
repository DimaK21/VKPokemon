package ru.kryu.vkpokemon.feature.pokemonlist.domain.model

data class PokemonPack(
    val count: Int,
    var nextlimit: String?,
    var nextoffset: String?,
    val pokemonList: MutableList<PokemonItem>
)
