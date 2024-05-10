package ru.kryu.vkpokemon.feature.pokemonlist.data.network.dto

data class PokemonListResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<ResultDto>
)
