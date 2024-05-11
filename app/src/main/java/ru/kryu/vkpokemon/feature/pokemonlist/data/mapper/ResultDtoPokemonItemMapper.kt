package ru.kryu.vkpokemon.feature.pokemonlist.data.mapper

import ru.kryu.vkpokemon.feature.pokemonlist.data.network.dto.ResultDto
import ru.kryu.vkpokemon.feature.pokemonlist.domain.model.PokemonItem

object ResultDtoPokemonItemMapper {

    fun map(resultDto: ResultDto): PokemonItem {
        val id = resultDto.url.split("/").lastOrNull() { it != "" && it != " " }?.toInt() ?: 0
        return PokemonItem(
            name = resultDto.name,
            id = id,
            icon = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
        )
    }
}