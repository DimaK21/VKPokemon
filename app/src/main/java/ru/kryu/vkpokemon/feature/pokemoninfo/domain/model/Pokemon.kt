package ru.kryu.vkpokemon.feature.pokemoninfo.domain.model

data class Pokemon(
    val id: Int,
    val name: String,
    val icon: String,
    val forms: List<String>,
    val height: Float,
    val weight: Float,
    val abilities: List<String>,
)
